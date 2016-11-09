package com.coworkio.dto.mapper

import com.coworkio.dto.UserDto
import com.coworkio.entity.domain.BaseInfo
import com.coworkio.entity.domain.User
import com.coworkio.entity.domain.enum.NotificationPreferences
import com.coworkio.entity.domain.enum.Role
import java.util.*

class UserDtoMapper : DtoMapper<User, UserDto> {

    override fun toDomain(dto: UserDto)
            = User(
                baseInfo = BaseInfo(Date(), false),
                firstName = dto.firstName,
                lastName = dto.lastName,
                role = Role.STUDENT,
                email = dto.email,
                password = dto.password,
                accountConfirmed = false,
                notificationPreferences = NotificationPreferences.ALL)

    override fun toDto(domain: User)
            = UserDto(domain.email, domain.password, domain.firstName, domain.lastName)

}