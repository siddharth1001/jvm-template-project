package com.example.app.healthcheck

import com.codahale.metrics.health.HealthCheck

class MyHealthCheck : HealthCheck() {
    // private val LOG by logger()

    override fun check(): Result {
        println("Performing health check!")
        return Result.healthy()
    }
}