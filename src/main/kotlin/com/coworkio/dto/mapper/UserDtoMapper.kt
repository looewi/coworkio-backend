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
open class UserDtoMapper : DtoMapper<User, UserDto> {

    private val BCRYPT_STRENGTH = 15

    @Autowired
    @Qualifier("authenticationService")
    private lateinit var authService: AuthenticationService

    override fun toDomain(dto: UserDto)
            = User(
                id = null,
                baseInfo = BaseInfo(Date(), false),
                firstName = dto.firstName,
                middleName = null,
                lastName = dto.lastName,
                role = Role.STUDENT,
                photoUrl = null,
                email = dto.email,
                password = BCryptPasswordEncoder(BCRYPT_STRENGTH).encode(dto.password),
                accountConfirmed = false,
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

}