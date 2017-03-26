package com.coworkio.repository

import com.coworkio.entity.domain.Curator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
open class CustomCuratorRepositoryImpl(@Autowired val mongoTemplate: MongoTemplate): CustomCuratorRepository {

    override fun findAllCuratorsByUniversityInfo(params: Map<String, String?>): List<Curator> {
        var criteria = emptyArray<Criteria>()
        params.map { Criteria.where(it.key).`is`(it.value) }.forEach { criteria += it }

        return mongoTemplate.find(Query.query(Criteria().andOperator(*criteria)), Curator::class.java)
    }

    override fun addProject(curatorId: String, projectId: String) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").`is`(curatorId)),
                Update().addToSet("projects", projectId),
                Curator::class.java
        )
    }
}