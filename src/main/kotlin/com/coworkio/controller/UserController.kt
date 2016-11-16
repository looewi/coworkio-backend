package com.coworkio.controller

import com.coworkio.dto.ProjectMinifiedVersionDto
import com.coworkio.dto.UserProfileDto
import com.coworkio.entity.domain.UserProject
import com.coworkio.service.domain.ProjectService
import com.coworkio.service.domain.UserService
import javassist.tools.web.BadHttpRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user")
open class UserController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var projectService: ProjectService

    @RequestMapping(value = "/emailIsAvailable", method = arrayOf(RequestMethod.GET))
    fun emailIsAvailable(@RequestParam email: String)
            = userService.isEmailAvailable(email)

    @RequestMapping(value = "/all", method = arrayOf(RequestMethod.GET))
    fun getAllUsers()
            = userService.findAll()

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.GET))
    fun getUserById(@PathVariable id: String)
            = userService.findUserProfileById(id)

    @RequestMapping(value = "/profile", method = arrayOf(RequestMethod.GET))
    fun getProfileOfCurrentUser(): UserProfileDto? {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val user = userService.findByEmail(auth.principal as String)
        return if (user != null) {
            userService.findUserProfileById(user.id!!)
        } else {
            null
        }
    }

    @RequestMapping(value = "/update", method = arrayOf(RequestMethod.POST))
    fun updateUser(@Validated @RequestBody userProfileDto: UserProfileDto, bindingResult: BindingResult): Boolean {
        if(bindingResult.hasErrors()) {
            throw BadHttpRequest()
        }
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val user = userService.findByEmail(auth.principal as String)
        return if (user != null) {
            userProfileDto.email = auth.principal as String
            userProfileDto.password = user.password

            userService.saveOrUpdate(userProfileDto)
            true
        } else {
            false
        }
    }

    @RequestMapping(value = "/getProjects", method = arrayOf(RequestMethod.GET))
    fun getUserProjects(): List<ProjectMinifiedVersionDto>? {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val user = userService.findByEmail(auth.principal as String)
        return if (user != null) {
            projectService.getProjectsByIds(user.projects?.map(UserProject::projectId))
        } else {
            null
        }
    }

}