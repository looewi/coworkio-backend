package com.coworkio.repository

import com.coworkio.entity.domain.Position
import com.coworkio.entity.domain.Project
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository


@Repository
open class ProjectRepositoryImpl: CustomProjectRepository {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    override fun addPosition(projectId: String, position: Position)
            = mongoTemplate.upsert(
                Query.query(Criteria.where("id").`is`(projectId)),
                Update().push("positions", position),
                Project::class.java
            ).upsertedId as String?


}