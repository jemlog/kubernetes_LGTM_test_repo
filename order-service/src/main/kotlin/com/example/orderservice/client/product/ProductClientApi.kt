package com.example.orderservice.client.product

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "product-service")
interface ProductClientApi {

    @GetMapping("/products")
    fun getProducts(): Int
}