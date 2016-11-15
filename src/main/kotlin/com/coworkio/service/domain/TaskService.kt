package com.coworkio.service.domain

import com.coworkio.dto.TaskDto
import com.coworkio.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class TaskService {

    @Autowired
    private lateinit var taskRepository: TaskRepository

    fun getTaskById(id: String)
            = taskRepository.findOne(id)

}