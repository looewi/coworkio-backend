package com.coworkio.dto.mapper

import com.coworkio.dto.TaskDto
import com.coworkio.entity.domain.BaseInfo
import com.coworkio.entity.domain.Task
import org.springframework.stereotype.Component
import java.util.*


@Component
open class TaskDtoMapper: DtoMapper<Task, TaskDto> {

    override fun toDomain(dto: TaskDto)
            = Task(
                id = dto.id,
                baseInfo = BaseInfo(Date(), true),
                title = dto.title,
                taskLevel = dto.taskLevel,
                taskType = dto.taskType,
                subtasks = dto.subtasks,
                parentTask = dto.parentTask,
                relatedTasks = dto.relatedTasks,
                description = dto.description,
                authorId = dto.authorId,
                assigneeId = dto.assigneeId,
                estimate = dto.estimate,
                priority = dto.priority,
                tags = dto.tags,
                status = dto.status,
                sprintId = dto.sprintId,
                projectId = dto.projectId,
                comments = null,
                dueDate = dto.dueDate
            )

    override fun toDto(domain: Task)
            = TaskDto(
            id = domain.id,
            title = domain.title,
            taskLevel = domain.taskLevel,
            taskType = domain.taskType,
            subtasks = domain.subtasks,
            parentTask = domain.parentTask,
            relatedTasks = domain.relatedTasks,
            description = domain.description,
            authorId = domain.authorId,
            assigneeId = domain.assigneeId,
            estimate = domain.estimate,
            priority = domain.priority,
            tags = domain.tags,
            status = domain.status,
            sprintId = domain.sprintId,
            projectId = domain.projectId,
            dueDate = domain.dueDate
    )
}