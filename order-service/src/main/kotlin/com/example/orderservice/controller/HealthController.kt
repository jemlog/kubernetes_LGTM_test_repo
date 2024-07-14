package com.example.orderservice.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    val log: Logger = LoggerFactory.getLogger(HealthController::class.java)

    @GetMapping("/health")
    fun health(): String{
        log.info("hello")
        return "up"
    }
}