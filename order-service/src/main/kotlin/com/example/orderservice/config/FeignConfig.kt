package com.example.orderservice.config

import feign.Feign
import feign.micrometer.MicrometerCapability
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig {

    @Bean
    fun feignBuilder(meterRegistry: MeterRegistry): Feign.Builder {
        return Feign.builder().addCapability(MicrometerCapability(meterRegistry));
    }
}