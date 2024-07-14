package com.example.productservice.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class ProductConsumer {

    val log: Logger = LoggerFactory.getLogger(ProductConsumer::class.java)

    @KafkaListener(topics = ["product-topic"])
    fun test(@Payload num: Long){
        log.info("answer is {}", num)
    }
}