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

    override fun updatePosition(projectId: String, position: Position) {
        val project = mongoTemplate.findById(projectId, Project::class.java)
        if(project.positions != null) {
            project.positions = project.positions!!.map {
                it ->
                if (it.id == position.id) {
                    it.employeeId = position.employeeId
                }
                it
            }
        }

        mongoTemplate.upsert(
                Query.query(Criteria.where("id").`is`(projectId)),
                        Update().set("positions", project.positions),
                        Project::class.java
        )
    }

    override fun getProjectsByIds(projectIds: List<String>): List<Project>
            = mongoTemplate.find(
                Query.query(Criteria.where("id").`in`(projectIds)),
                Project::class.java
            )

    override fun findProjectByTitle(title: String): List<Project>?
            = mongoTemplate.find(Query.query(Criteria.where("title").regex("*$title.*")), Project::class.java)


}