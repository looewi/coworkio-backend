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

    override fun findUsers(firstName: String?, lastName: String?, university: String?, faculty: String?): List<User>? {
        val criterias = mutableListOf<Criteria>()
        if(firstName != null) criterias += Criteria.where("firstName").`is`(firstName)
        if(lastName != null) criterias += Criteria.where("lastName").`is`(lastName)
        if(university != null) criterias += Criteria.where("university.university").`is`(university)
        if(faculty != null) criterias += Criteria.where("university.faculty").`is`(faculty)

        return mongoTemplate.find(
                Query.query(Criteria().andOperator(*criterias.toTypedArray())),
                User::class.java
        )

    }

}