package com.example.orderservice.controller

import com.example.orderservice.application.OrderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val orderService: OrderService
) {
    @PostMapping("/order")
    fun createOrder(): String{
       orderService.placeOrder()
        return "success"
    }
}