package com.coworkio.controller

import com.coworkio.service.domain.ProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/project/{projectId}/position")
open class PositionController {

    //add position to project
    //set user to position

    @Autowired
    private lateinit var projectService: ProjectService

    @RequestMapping(value = "/all", method = arrayOf(RequestMethod.GET))
    open fun getAllPositionsOfProject(@PathVariable projectId: String)
            = projectService.getPositionsByProject(projectId)

    @RequestMapping(value = "/{positionId}", method = arrayOf(RequestMethod.GET))
    open fun getPositionById(@PathVariable projectId: String, @PathVariable positionId: String)
            = projectService.getPositionById(projectId, positionId)
}