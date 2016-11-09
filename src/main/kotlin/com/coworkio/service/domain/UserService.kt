package com.coworkio.service.domain

import com.coworkio.entity.domain.User
import com.coworkio.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
open class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    open fun count(): Long
            = userRepository.count()

    open fun delete(id: String)
            = userRepository.delete(id)

    open fun delete(user: User)
            = userRepository.delete(user)

    open fun saveOrUpdate(user: User): User {
        user.baseInfo.lastModifiedDate = Date()
        return if(user.id == null) {
            userRepository.insert(user)
        } else {
            userRepository.save(user)
        }
    }

    open fun findAll(): Iterable<User>
            = userRepository.findAll()

    open fun findById(id: String)
            = userRepository.findOne(id)

    open fun removeSilently(user: User) {
        user.baseInfo.isDeleted = true
        saveOrUpdate(user)
    }

    open fun exists(user: User)
            = userRepository.findUserByEmailAndPassword(user.email, user.password) != null
}