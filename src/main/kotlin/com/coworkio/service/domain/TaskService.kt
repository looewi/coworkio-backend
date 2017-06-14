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

    private fun getAuthorAndAssignee(authorId: String?, assigneeId: String?): Pair<UserMinDto?, UserMinDto?> {
        val assignee =
                if(assigneeId != null)
                    userRepository.findOne(assigneeId)?.toUserMinDto()
                else null
        val author =
                if(authorId != null)
                    userRepository.findOne(authorId)?.toUserMinDto()
                else null
        return Pair(author, assignee)
    }

    fun getTaskById(id: String): TaskDtoWithUserMin? {
        val task = taskRepository.findOne(id)
        val userInfo = getAuthorAndAssignee(task.authorId, task.assigneeId)
        return task?.toTaskWithUserMinDto(userInfo.first, userInfo.second) ?: null
    }

    fun getAllTasks(projectId: String): List<TaskDtoWithUserMin>
            = taskRepository.findAll()
                .filter { it.projectId == projectId }
                .map {
                    val userInfo = getAuthorAndAssignee(it.authorId, it.assigneeId)
                    it.toTaskWithUserMinDto(userInfo.first, userInfo.second)
                }

    fun saveOrUpdate(taskDto: TaskDto): TaskDto {
        return if(taskDto.id != null) {
            taskDtoMapper.toDto(taskRepository.save(taskDtoMapper.toDomain(taskDto)))
        } else {
            taskDtoMapper.toDto(taskRepository.insert(taskDtoMapper.toDomain(taskDto)))
        }
    }

    fun find(projectId: String, title: String?, priority: String?, assignee: String?): List<TaskDto> {
        return taskRepository.find(projectId, title, priority, assignee)?.map {
            taskDtoMapper.toDto(it)
        } ?: emptyList()
    }

}