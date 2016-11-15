package com.coworkio.service.domain

import com.coworkio.dto.TaskDto
import com.coworkio.dto.mapper.TaskDtoMapper
import com.coworkio.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class TaskService {

    @Autowired
    private lateinit var taskRepository: TaskRepository

    @Autowired
    private lateinit var taskDtoMapper: TaskDtoMapper

    fun getTaskById(id: String)
            = taskDtoMapper.toDto(taskRepository.findOne(id)!!)

    fun getAllTasks(projectId: String): List<TaskDto>
            = taskRepository.findAll()
                .filter { it -> it.projectId == projectId }
                .map { it -> taskDtoMapper.toDto(it) }

}