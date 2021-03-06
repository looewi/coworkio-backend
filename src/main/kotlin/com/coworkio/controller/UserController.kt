package com.coworkio.controller

import com.coworkio.API_CONSTANT
import com.coworkio.dto.DashboardProjectDto
import com.coworkio.dto.ProjectMinifiedVersionDto
import com.coworkio.dto.UserProfileDto
import com.coworkio.entity.domain.Project
import com.coworkio.entity.domain.User
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
@RequestMapping("$API_CONSTANT/user")
open class UserController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var projectService: ProjectService

    @RequestMapping(value = "/emailIsAvailable", method = arrayOf(RequestMethod.GET))
    fun emailIsAvailable(@RequestParam email: String)
            = userService.isEmailAvailable(email)

    @RequestMapping(value = "/all", method = arrayOf(RequestMethod.GET))
    fun getAllUsers() = userService.findAll()

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.GET))
    fun getUserById(@PathVariable id: String)
            = userService.findUserProfileById(id)

    @RequestMapping(value = "/profile", method = arrayOf(RequestMethod.GET))
    fun getProfileOfCurrentUser(): UserProfileDto? {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val user = userService.findByEmail(auth.principal as String)
        return when(user) {
            null -> null
            else -> userService.findUserProfileById(user.id!!)
        }
    }

    @RequestMapping(value = "/update", method = arrayOf(RequestMethod.POST))
    fun updateUser(@Validated @RequestBody userProfileDto: UserProfileDto, bindingResult: BindingResult): Boolean {
        if(bindingResult.hasErrors()) {
            throw BadHttpRequest()
        }
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val user = userService.findByEmail(auth.principal as String)
        return when(user) {
            null -> false
            else -> {
                userProfileDto.email = auth.principal as String
                userProfileDto.password = user.password

                userService.saveOrUpdate(userProfileDto)
                true
            }
        }
    }

    @RequestMapping(value = "/getProjects", method = arrayOf(RequestMethod.GET))
    fun getUserProjects(): List<DashboardProjectDto>? {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val user = userService.findByEmail(auth.principal as String)
        return when(user) {
            null -> null
            else -> user.projects?.map {
                DashboardProjectDto(projectService.getProjectById(it.projectId)!!, it.isCurrent)
            }
        }
    }

    @RequestMapping(value = "/find", method = arrayOf(RequestMethod.GET))
    fun findUser(@RequestParam firstName: String?, @RequestParam lastName: String?,
                 @RequestParam university: String?, @RequestParam faculty: String?): List<User> {
        return userService.find(firstName, lastName, university, faculty) ?: emptyList()
    }

}