package com.coworkio.repository

import com.coworkio.entity.domain.User
import com.coworkio.entity.domain.UserProject
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String>, CustomUserRepository {

    fun findUserByEmailAndPassword(email: String, password: String): User?
    fun findUserByEmail(email: String): User?
}