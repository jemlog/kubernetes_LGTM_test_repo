## Otel Collector Setting

```yaml
apiVersion: apps/v1 
kind: Deployment 
metadata: 
  name: opentelemetry-collector-deployment 
  labels: 
    app: opentelemetry-collector
spec: 
  selector: 
    matchLabels: 
      app: opentelemetry-collector
  template: 
    metadata: 
      labels: 
        app: opentelemetry-collector
    spec: 
      containers: 
      - name: opentelemetry-collector
        image: otel/opentelemetry-collector:latest 
        command:
        - /otelcol
        - --config=/conf/collector.yaml
        ports: 
        - name: jaeger-compact
          containerPort: 6831
          protocol: UDP
        - name: jaeger-grpc
          containerPort: 14250
          protocol: TCP
        - name: jaeger-thrift
          containerPort: 14268
          protocol: TCP
        - name: otlp
          containerPort: 4317
          protocol: TCP
        - name: otlp-http # Spring Application의 Otel Exporter를 통해서 해당 Port로 전송
          containerPort: 4318
          protocol: TCP
        - name: zipkin
          containerPort: 9411
          protocol: TCP
        volumeMounts: 
        - name: opentelemetry-collector-config
          mountPath: /conf
      volumes:
      - name: opentelemetry-collector-config
        configMap:
          name: opentelemetry-collector-configmap
          items:
          - key: collector.yaml
            path: collector.yaml
---
apiVersion: v1
kind: ConfigMap
metadata:
  name: opentelemetry-collector-configmap
data:
  collector.yaml: |
    receivers:
      otlp:
        protocols:
          grpc:
            endpoint: 0.0.0.0:4317
          http:
            endpoint: 0.0.0.0:4318
    processors:
    exporters:
      otlp:
        endpoint: tempo-service:4317 # otlp 형식으로 쿠버네티스 내 tempo-service 서비스로 가공된 데이터 전송
        tls:
          insecure: true
      logging:
        loglevel: info
    service:
      pipelines:
        traces:
          receivers:
          - otlp
          processors: []
          exporters:
          - logging
          - otlp
---
apiVersion: v1 
kind: Service 
metadata: 
  name: opentelemetry-collector-service 
  labels: 
    app: opentelemetry-collector
spec: 
  selector: 
    app: opentelemetry-collector
  ports: 
  - name: jaeger-compact
    port: 6831
    targetPort: 6831
    protocol: UDP
  - name: jaeger-grpc
    port: 14250
    targetPort: 14250
    protocol: TCP
  - name: jaeger-thrift
    port: 14268
    targetPort: 14268
    protocol: TCP
  - name: otlp
    port: 4317
    targetPort: 4317
    protocol: TCP
    appProtocol: grpc
  - name: otlp-http
    port: 4318
    targetPort: 4318
    protocol: TCP
  - name: zipkin
    port: 9411
    targetPort: 9411
    protocol: TCP
```

## Spring Application Otlp EndPoint

```yaml
management:
  otlp:
    tracing:
      endpoint: http://opentelemetry-collector-service:4318/v1/traces
```

