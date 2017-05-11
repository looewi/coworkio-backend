package com.coworkio.service.domain

import com.coworkio.dto.*
import com.coworkio.dto.mapper.TaskDtoMapper
import com.coworkio.repository.TaskRepository
import com.coworkio.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class TaskService {

    @Autowired
    private lateinit var taskRepository: TaskRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var taskDtoMapper: TaskDtoMapper

    fun getTaskById(id: String): TaskDtoWithUserMin? {
        val task = taskRepository.findOne(id)
        val assignee = userRepository.findOne(task.assigneeId).toUserMinDto()
        val author = userRepository.findOne(task.authorId).toUserMinDto()
        return task?.toTaskWithUserMinDto(author, assignee) ?: null
    }

    fun getAllTasks(projectId: String): List<TaskDtoWithUserMin>
            = taskRepository.findAll()
                .filter { it.projectId == projectId }
                .map {
                    val assignee = userRepository.findOne(it.assigneeId).toUserMinDto()
                    val author = userRepository.findOne(it.authorId).toUserMinDto()
                    it.toTaskWithUserMinDto(author, assignee)
                }

    fun saveOrUpdate(taskDto: TaskDto): TaskDto {
        return if(taskDto.id != null) {
            taskDtoMapper.toDto(taskRepository.save(taskDtoMapper.toDomain(taskDto)))
        } else {
            taskDtoMapper.toDto(taskRepository.insert(taskDtoMapper.toDomain(taskDto)))
        }
    }

}