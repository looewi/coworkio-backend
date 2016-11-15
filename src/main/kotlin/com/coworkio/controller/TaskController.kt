package com.coworkio.controller

import com.coworkio.dto.TaskDto
import com.coworkio.service.domain.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = "/task")
open class TaskController {

    @Autowired
    private lateinit var taskService: TaskService


    @RequestMapping(value = "/add", method = arrayOf(RequestMethod.POST))
    fun addTaskToProject(@Validated @RequestBody taskDto: TaskDto) {
        throw NotImplementedError("not implemented")
    }

    @RequestMapping(value = "/update", method = arrayOf(RequestMethod.POST))
    fun updateTask(@Validated @RequestBody taskDto: TaskDto) {
        throw NotImplementedError("not implemented")
    }

    @RequestMapping(value = "/{projectId}/all", method = arrayOf(RequestMethod.GET))
    fun getAllTasks(@PathVariable projectId: String)
            = taskService.getAllTasks(projectId)

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.GET))
    fun getTaskById(@PathVariable id: String)
            = taskService.getTaskById(id)

}