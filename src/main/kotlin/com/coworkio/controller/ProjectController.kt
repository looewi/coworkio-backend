package com.coworkio.controller

import com.coworkio.API_CONSTANT
import com.coworkio.controller.exception.ProjectNotCreatedException
import com.coworkio.dto.NewProjectDto
import com.coworkio.entity.domain.Project
import com.coworkio.service.domain.ProjectService
import com.coworkio.service.domain.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = "$API_CONSTANT/project")
open class ProjectController {

    @Autowired
    private lateinit var projectService: ProjectService

    @Autowired
    private lateinit var userService: UserService

    @RequestMapping(value = "/create", method = arrayOf(RequestMethod.POST))
    fun createProject(
            @Validated @RequestBody newProjectDto: NewProjectDto,
            bindingResult: BindingResult,
            authentication: Authentication): ResponseEntity<String?> {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("-1")
        }
        val projectId = projectService.saveOrUpdate(newProjectDto).id
        return if (projectId != null) {
            userService.addUserPosition(projectId = projectId, userEmail = authentication.principal as String)
            ResponseEntity.ok(projectId)
        } else {
            throw ProjectNotCreatedException()
        }
    }

    @RequestMapping(value = "/all", method = arrayOf(RequestMethod.GET))
    fun getAllProjects() = projectService.getAllProjects()

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.GET))
    fun getProjectById(@PathVariable id: String): Project?
            = projectService.getProjectById(id)

    @RequestMapping(value = "/find", method = arrayOf(RequestMethod.GET))
    fun findProjects(@PathVariable title: String): List<Project> {
        return projectService.findByTitle(title) ?: emptyList()
    }

}