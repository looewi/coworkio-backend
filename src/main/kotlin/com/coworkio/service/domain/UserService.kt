package com.coworkio.service.domain

import com.coworkio.dto.UserDto
import com.coworkio.entity.domain.User
import com.coworkio.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
open class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun count(): Long
            = userRepository.count()

    fun delete(id: String)
            = userRepository.delete(id)

    fun delete(user: User)
            = userRepository.delete(user)

    fun saveOrUpdate(user: User): User {
        user.baseInfo.lastModifiedDate = Date()
        return if(user.id == null) {
            userRepository.insert(user)
        } else {
            userRepository.save(user)
        }
    }

    fun findAll(): Iterable<User>
            = userRepository.findAll()

    fun findById(id: String)
            = userRepository.findOne(id)

    fun isEmailAvailable(email: String)
            = userRepository.findUserByEmail(email) == null

    fun removeSilently(user: User) {
        user.baseInfo.isDeleted = true
        saveOrUpdate(user)
    }

    fun exists(user: UserDto)
            = userRepository.findUserByEmailAndPassword(user.email, user.password) != null

    fun findByEmail(email: String)
            = userRepository.findUserByEmail(email)
}