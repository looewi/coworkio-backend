package com.coworkio.controller

import com.coworkio.dto.ProjectDto
import com.coworkio.service.domain.ProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


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

}