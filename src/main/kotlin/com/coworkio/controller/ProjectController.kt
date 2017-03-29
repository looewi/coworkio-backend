package com.coworkio.controller

import com.coworkio.API_CONSTANT
import com.coworkio.dto.ProjectDto
import com.coworkio.service.domain.ProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = "$API_CONSTANT/project")
open class ProjectController {

    @Autowired
    private lateinit var projectService: ProjectService

    @RequestMapping(value = "/create", method = arrayOf(RequestMethod.POST))
    fun createProject(@Validated @RequestBody projectDto: ProjectDto, bindingResult: BindingResult): ResponseEntity<String?> {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("-1")
        }
        val id = projectService.saveOrUpdate(projectDto).id
        return ResponseEntity.ok(id)
    }

    @RequestMapping(value = "/all", method = arrayOf(RequestMethod.GET))
    fun getAllProjects(): List<ProjectDto>? {
        return projectService.getAllProjects()
    }

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.GET))
    fun getProjectById(@PathVariable id: String): ProjectDto?
            = projectService.getProjectDtoById(id)

}