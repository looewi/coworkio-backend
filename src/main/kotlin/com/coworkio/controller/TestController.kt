package com.coworkio.controller

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
open class TestController {

    @RequestMapping(value = "/hi")
    fun testMapping(): String {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        return "Hello world, " + auth.principal
    }
}
