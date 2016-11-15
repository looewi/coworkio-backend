package com.coworkio.controller

import com.coworkio.service.domain.UserService
import com.sun.org.apache.bcel.internal.generic.GETFIELD
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user")
open class UserController {

    @Autowired
    private lateinit var userService: UserService

    @RequestMapping(value = "/emailIsAvailable", method = arrayOf(RequestMethod.GET))
    fun emailIsAvailable(@RequestParam email: String)
            = userService.isEmailAvailable(email)

    @RequestMapping(value = "/all", method = arrayOf(RequestMethod.GET))
    fun getAllUsers()
            = userService.findAll()

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.GET))
    fun getUserById(@PathVariable id: String)
            = userService.findById(id)

}