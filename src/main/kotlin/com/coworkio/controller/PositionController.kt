package com.coworkio.controller

import com.coworkio.API_CONSTANT
import com.coworkio.dto.PositionDto
import com.coworkio.service.domain.ProjectService
import javassist.tools.web.BadHttpRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = "$API_CONSTANT/project/{projectId}/position")
open class PositionController {

    @Autowired
    private lateinit var projectService: ProjectService

    @RequestMapping(value = "/all", method = arrayOf(RequestMethod.GET))
    open fun getAllPositionsOfProject(@PathVariable projectId: String)
            = projectService.getPositionsByProject(projectId)

    @RequestMapping(value = "/{positionId}", method = arrayOf(RequestMethod.GET))
    open fun getPositionById(@PathVariable projectId: String, @PathVariable positionId: String)
            = projectService.getPositionById(projectId, positionId)

    @RequestMapping(value = "/add", method = arrayOf(RequestMethod.POST))
    open fun addPosition(@Validated @RequestBody positionDto: PositionDto, @PathVariable projectId: String,
                         bindingResult: BindingResult): String? {
        if(bindingResult.hasErrors()) {
            throw BadHttpRequest(Exception("Position data is invalid"))
        }
        return projectService.addPosition(projectId, positionDto)
    }

    @RequestMapping(value = "/{positionId}/employ", method = arrayOf(RequestMethod.POST))
    open fun setUserToPosition(@PathVariable projectId: String, @PathVariable positionId: String, @RequestParam userId: String)
            = projectService.setUserToPosition(projectId, positionId, userId)

}