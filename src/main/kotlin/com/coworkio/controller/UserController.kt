package com.coworkio.controller

import com.coworkio.service.domain.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/user")
open class UserController {

    @Autowired
    private lateinit var userService: UserService

    @RequestMapping(value = "/emailIsAvailable", method = arrayOf(RequestMethod.GET))
    fun emailIsAvailable(@RequestParam email: String)
            = userService.isEmailAvailable(email)

}