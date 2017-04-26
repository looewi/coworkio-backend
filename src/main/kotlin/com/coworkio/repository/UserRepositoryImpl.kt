package com.coworkio.repository

import com.coworkio.entity.domain.User
import com.coworkio.entity.domain.UserProject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
open class UserRepositoryImpl: CustomUserRepository {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    override fun addPosition(userProject: UserProject, userEmail: String) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("email").`is`(userEmail)),
                Update().addToSet("projects", userProject),
                User::class.java
        )
    }
}