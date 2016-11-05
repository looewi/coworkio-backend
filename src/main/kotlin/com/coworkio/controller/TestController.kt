package com.coworkio.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
open class TestController {

    @RequestMapping(value = "/hi")
    fun testMapping(): String {
        return "Hello world"
    }
}
