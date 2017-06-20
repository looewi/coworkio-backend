package com.coworkio.controller

import com.coworkio.API_CONSTANT
import com.coworkio.dto.TaskDto
import com.coworkio.service.domain.TaskService
import com.coworkio.service.domain.UserService
import javassist.tools.web.BadHttpRequest
import org.apache.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = "$API_CONSTANT/task")
open class TaskController {

    @Autowired
    private lateinit var taskService: TaskService

    @Autowired
    private lateinit var userService: UserService

    @RequestMapping(value = "/add", method = arrayOf(RequestMethod.POST))
    fun addTaskToProject(@Validated @RequestBody taskDto: TaskDto, bindingResult: BindingResult): TaskDto? {
        if(bindingResult.hasErrors()) {
            throw BadHttpRequest()
        }
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        taskDto.authorId = userService.findByEmail(auth.principal as String)?.id!!
        return taskService.saveOrUpdate(taskDto)
    }

    @RequestMapping(value = "/update", method = arrayOf(RequestMethod.POST))
    fun updateTask(@Validated @RequestBody taskDto: TaskDto, bindingResult: BindingResult): TaskDto? {
        if(bindingResult.hasErrors()) {
            throw BadHttpRequest()
        }
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        taskDto.authorId = userService.findByEmail(auth.principal as String)?.id!!
        return taskService.saveOrUpdate(taskDto)
    }

    @RequestMapping(value = "/{projectId}/all", method = arrayOf(RequestMethod.GET))
    fun getAllTasks(@PathVariable projectId: String)
            = taskService.getAllTasks(projectId)

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.GET))
    fun getTaskById(@PathVariable id: String)
            = taskService.getTaskById(id)

    @RequestMapping(value = "/{projectId}/find", method = arrayOf(RequestMethod.GET))
    fun getTaskById(@PathVariable projectId: String, @RequestParam title: String?, @RequestParam priority: String?,
                    @RequestParam assignee: String?)
            = taskService.find(projectId, title, priority, assignee)


}