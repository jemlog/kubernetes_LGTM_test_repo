package com.example.orderservice.application

import com.example.orderservice.client.product.ProductClient
import com.example.orderservice.producer.ProductProducer
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val productClient: ProductClient,
    private val productProducer: ProductProducer,
) {

    fun placeOrder(){
        val productCount = productClient.getProducts()
        productProducer.send()
    }
}