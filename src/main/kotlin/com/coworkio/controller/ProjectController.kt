package com.coworkio.controller

import com.coworkio.dto.PositionDto
import com.coworkio.dto.ProjectDto
import com.coworkio.service.domain.ProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = "/project")
open class ProjectController {

    @Autowired
    private lateinit var projectService: ProjectService

    @RequestMapping(value = "/create", method = arrayOf(RequestMethod.POST))
    open fun createProject(@Validated @RequestBody projectDto: ProjectDto, bindingResult: BindingResult): ResponseEntity<String?> {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("-1")
        }
        val id = projectService.saveOrUpdate(projectDto).id
        return ResponseEntity.ok(id)
    }

    @RequestMapping(value = "/add/position", method = arrayOf(RequestMethod.POST))
    open fun addPosition(@Validated @RequestBody positionDto: PositionDto, bindingResult: BindingResult,
                         @RequestParam projectId: String): Boolean {
        projectService.addPosition()
        return true
    }

    @RequestMapping(value = "/all", method = arrayOf(RequestMethod.GET))
    open fun getAllProjects(): List<ProjectDto>? {
        return projectService.getAllProjects()
    }

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.GET))
    open fun getProjectById(@PathVariable id: String): ProjectDto?
            = projectService.getProjectDtoById(id)

}