package com.coworkio.controller

import com.coworkio.dto.UserProfileDto
import com.coworkio.service.domain.UserService
import com.sun.org.apache.bcel.internal.generic.GETFIELD
import javassist.tools.web.BadHttpRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
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

    @RequestMapping(value = "/update", method = arrayOf(RequestMethod.POST))
    fun updateUser(@Validated @RequestBody userProfileDto: UserProfileDto, bindingResult: BindingResult): Boolean {
        if(bindingResult.hasErrors()) {
            throw BadHttpRequest()
        }

        return true
    }

}