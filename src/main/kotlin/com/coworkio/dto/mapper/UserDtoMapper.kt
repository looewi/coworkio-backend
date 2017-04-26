package com.coworkio.dto.mapper

import com.coworkio.dto.UserDto
import com.coworkio.entity.domain.BaseInfo
import com.coworkio.entity.domain.User
import com.coworkio.entity.domain.enum.NotificationPreferences
import com.coworkio.entity.domain.enum.Role
import org.springframework.stereotype.Component
import java.util.*

@Component
open class UserDtoMapper : DtoMapper<User, UserDto> {

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
                password = dto.password,
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

}