package com.coworkio.controller

import com.coworkio.API_CONSTANT
import com.coworkio.entity.domain.Curator
import com.coworkio.service.domain.CuratorService
import com.coworkio.service.domain.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$API_CONSTANT/curator")
open class CuratorController(
        @Autowired val userService: UserService,
        @Autowired val curatorService: CuratorService) {

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun getAllCurators(): List<Curator> {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val user = userService.findByEmail(auth.principal as String)

        val universityInfo = user!!.university
        return curatorService.getAllCurators(
                when(universityInfo) {
                    null -> emptyMap<String, String?>()
                    else -> { mapOf(
                                "university" to universityInfo.university,
                                "faculty" to universityInfo.faculty,
                                "department" to universityInfo.department)
                    }
                }
        )
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun addCuratorToProject(
            @RequestParam projectId: String,
            @RequestParam curatorId: String)
            = curatorService.addProject(curatorId, projectId)

    @RequestMapping(method = arrayOf(RequestMethod.DELETE))
    fun deleteCuratorFromProject(@RequestParam projectId: String, @RequestParam curatorId: String)
            = curatorService.deleteProject(projectId, curatorId)
}