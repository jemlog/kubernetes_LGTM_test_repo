package com.example.orderservice.producer

import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ProductProducer(
    val kafkaTemplate: KafkaTemplate<String,Long>
) {

   fun send(){
       kafkaTemplate.send(ProducerRecord("product-topic",2L))
   }
}