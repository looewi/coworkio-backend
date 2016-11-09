package com.coworkio.dto.mapper

import com.coworkio.dto.UserDto
import com.coworkio.entity.domain.BaseInfo
import com.coworkio.entity.domain.User
import com.coworkio.entity.domain.enum.NotificationPreferences
import com.coworkio.entity.domain.enum.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

class UserDtoMapper : DtoMapper<User, UserDto> {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    override fun toDomain(dto: UserDto)
            = User(
                baseInfo = BaseInfo(Date(), false),
                firstName = dto.firstName,
                lastName = dto.lastName,
                role = Role.STUDENT,
                email = dto.email,
                password = passwordEncoder.encode(dto.password),
                accountConfirmed = false,
                notificationPreferences = NotificationPreferences.ALL)

    override fun toDto(domain: User)
            = UserDto(domain.email, domain.password, domain.firstName, domain.lastName)

}