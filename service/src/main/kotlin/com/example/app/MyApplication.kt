package com.example.app

import com.example.app.healthcheck.MyHealthCheck
import com.example.app.resources.RootResource
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.inject.Guice
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import java.text.SimpleDateFormat

class MyApplication : Application<AppConfiguration>() {
    override fun run(config: AppConfiguration, env: Environment) {
        val name = config.getDefaultName()
        // todo: Add modules to injector
        val injector = Guice.createInjector()
        // Register Resources
        getResourceClasses().forEach { env.jersey().register(injector.getInstance(it)) }
        // Register Healthcheck
        env.healthChecks().register("template", MyHealthCheck())
    }

    override fun initialize(bootstrap: Bootstrap<AppConfiguration>) {
        bootstrap.objectMapper
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .registerModule(JavaTimeModule())
            .registerKotlinModule() // If u remove this is_admin field in user will be deserialized(as response JSON) as _admin
            .also {
                it.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                it.dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            }
    }

    fun getResourceClasses(): List<Class<*>> = listOf(
        RootResource::class.java
    )
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        val fileLocation = "./service/src/main/resources/server-config.yml"
        val myargs = listOf("server", fileLocation).toTypedArray()
        MyApplication().run(*myargs)
    } else MyApplication().run(*args)
}