package com.coworkio.service.domain

import com.coworkio.dto.UserDto
import com.coworkio.dto.UserProfileDto
import com.coworkio.dto.mapper.UserProfileDtoMapper
import com.coworkio.entity.domain.User
import com.coworkio.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
open class UserService(@Autowired val userRepository: UserRepository, @Autowired val userProfileDtoMapper: UserProfileDtoMapper) {

    fun count(): Long = userRepository.count()

    fun delete(user: User) = userRepository.delete(user)

    fun saveOrUpdate(user: User): User {
        user.baseInfo.lastModifiedDate = Date()
        return when (user.id) {
            null -> userRepository.insert(user)
            else -> userRepository.save(user)
        }
    }

    fun saveOrUpdate(userProfileDto: UserProfileDto): UserProfileDto
            = when(userProfileDto.id) {
                null -> userProfileDtoMapper.toDto(userRepository.insert(userProfileDtoMapper.toDomain(userProfileDto)))
                else -> userProfileDtoMapper.toDto(userRepository.save(userProfileDtoMapper.toDomain(userProfileDto)))
            }

    fun findAll(): Iterable<User> = userRepository.findAll()

    fun findById(id: String): User = userRepository.findOne(id)

    fun findUserProfileById(id: String): UserProfileDto
            = userProfileDtoMapper.toDto(userRepository.findOne(id))

    fun isEmailAvailable(email: String)
            = userRepository.findUserByEmail(email) == null

    fun exists(user: UserDto)
            = userRepository.findUserByEmailAndPassword(user.email, user.password) != null

    fun findByEmail(email: String) = userRepository.findUserByEmail(email)
}