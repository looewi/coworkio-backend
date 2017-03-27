package com.coworkio.dto.mapper

import com.coworkio.dto.UserDto
import com.coworkio.entity.domain.BaseInfo
import com.coworkio.entity.domain.User
import com.coworkio.entity.domain.enum.NotificationPreferences
import com.coworkio.entity.domain.enum.Role
import com.coworkio.service.security.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.*

@Component
open class UserDtoMapper(@Autowired @Qualifier val authService: AuthenticationService) : DtoMapper<User, UserDto> {

    override fun toDomain(dto: UserDto)
            = User(
                id = dto.id,
                baseInfo = BaseInfo(Date(), false),
                firstName = dto.firstName,
                middleName = null,
                lastName = dto.lastName,
                role = Role.STUDENT,
                photoUrl = null,
                email = dto.email,
                password = BCryptPasswordEncoder(BCRYPT_STRENGTH).encode(dto.password),
                accountConfirmed = true,
                notificationPreferences = NotificationPreferences.ALL,
                github = null,
                accounts = null,
                phoneNumber = null,
                projects = null,
                skills = null,
                university = null
            )

    override fun toDto(domain: User)
            = UserDto(domain.email, domain.password, domain.firstName, domain.lastName)

    companion object {
        private val BCRYPT_STRENGTH = 15
    }
}