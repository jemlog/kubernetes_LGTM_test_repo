package com.example.productservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @GetMapping("/products")
    fun getProducts(): Int{
        return 1
    }
}