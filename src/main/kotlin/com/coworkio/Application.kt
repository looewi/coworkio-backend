package com.coworkio

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan

@EnableCaching
@SpringBootApplication
@EnableAutoConfiguration
open class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
