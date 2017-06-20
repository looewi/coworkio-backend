package com.coworkio.repository

import com.coworkio.entity.domain.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

/**
 * Created by Mary on 6/14/2017.
 */

@Repository
open class TaskRepositoryImpl: CustomTaskRepository {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    override fun find(projectId: String, title: String?, priority: String?, assignee: String?): List<Task>? {
        val criterias = mutableListOf<Criteria>()
        if(title != null) criterias += Criteria.where("firstName").`is`(title)
        if(priority != null) criterias += Criteria.where("priority").`is`(priority)
        if(assignee != null) criterias += Criteria.where("assigneeId").`is`(assignee)

        return mongoTemplate.find(
                Query.query(Criteria.where("projectId").`is`(projectId).andOperator(*criterias.toTypedArray())),
                Task::class.java
        )
    }

}