package com.example.orderservice.client.product

import org.springframework.stereotype.Component

@Component
class ProductClient(
    private val productClientApi: ProductClientApi
) {

    fun getProducts(): Int{
        return productClientApi.getProducts()
    }
}