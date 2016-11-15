package com.coworkio.dto

import com.coworkio.entity.domain.enum.Priority
import com.coworkio.entity.domain.enum.TaskLevel
import com.coworkio.entity.domain.enum.TaskType
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.validation.annotation.Validated
import java.io.Serializable
import java.util.*
import javax.validation.constraints.NotNull

@Validated
open class TaskDto: Serializable {

    var id: String? = null

    @NotEmpty
    var title: String = ""

    var taskLevel: TaskLevel = TaskLevel.TASK

    var taskType: TaskType = TaskType.DEVELOPMENT

    var subtasks: List<String>? = null

    var parentTask: String? = null

    var relatedTasks: List<String>? = null

    var description: String? = null

    lateinit var authorId: String

    var assigneeId: String? = null

    var estimate: Double? = null

    var priority: Priority = Priority.NORMAL

    var tags: List<String>? = null

    var status: Int = 1

    var sprintId: String? = null

    @NotNull
    lateinit var projectId: String

//    var comments: List<CommentDto> = null

    var dueDate: Date? = null

    constructor()

    constructor(id: String?, title: String, taskLevel: TaskLevel, taskType: TaskType, subtasks: List<String>?, parentTask: String?, relatedTasks: List<String>?, description: String?, authorId: String, assigneeId: String?, estimate: Double?, priority: Priority, tags: List<String>?, status: Int, sprintId: String?, projectId: String, dueDate: Date?) {
        this.id = id
        this.title = title
        this.taskLevel = taskLevel
        this.taskType = taskType
        this.subtasks = subtasks
        this.parentTask = parentTask
        this.relatedTasks = relatedTasks
        this.description = description
        this.authorId = authorId
        this.assigneeId = assigneeId
        this.estimate = estimate
        this.priority = priority
        this.tags = tags
        this.status = status
        this.sprintId = sprintId
        this.projectId = projectId
        this.dueDate = dueDate
    }
}