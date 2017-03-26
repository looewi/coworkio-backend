package com.coworkio.dto.mapper

import com.coworkio.dto.UserProfileDto
import com.coworkio.entity.domain.BaseInfo
import com.coworkio.entity.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
open class UserProfileDtoMapper(
        @Autowired val positionDtoMapper: PositionDtoMapper,
        @Autowired val socialAccountDtoMapper: SocialAccountDtoMapper,
        @Autowired val universityDtoMapper: UniversityDtoMapper,
        @Autowired val userProjectDtoMapper: UserProjectDtoMapper,
        @Autowired val userSkillDtoMapper: UserSkillDtoMapper): DtoMapper<User, UserProfileDto> {

    override fun toDomain(dto: UserProfileDto)
            = User(
                id = dto.id,
                baseInfo = BaseInfo(Date(), true),
                firstName = dto.firstName,
                middleName = dto.middleName,
                lastName = dto.lastName,
                university = dto.university.let {
                    when (it) {
                        null -> null
                        else -> universityDtoMapper.toDomain(it)
                    }
                },
                accountConfirmed = dto.accountConfirmed,
                accounts = dto.accounts?.map { socialAccountDtoMapper.toDomain(it) },
                email = dto.email,
                password = dto.password,
                github = dto.github,
                role = dto.role,
                projects = dto.projects?.map { userProjectDtoMapper.toDomain(it) },
                phoneNumber = dto.phoneNumber,
                photoUrl = dto.photoUrl,
                skills = dto.skills?.map { userSkillDtoMapper.toDomain(it) },
                notificationPreferences = dto.notificationPreferences
    )

    override fun toDto(domain: User)
            = UserProfileDto(
                id = domain.id!!,
                firstName = domain.firstName,
                middleName = domain.middleName,
                lastName = domain.lastName,
                university = domain.university.let {
                    when (it) {
                        null -> null
                        else -> universityDtoMapper.toDto(it)
                    }
                },
                accountConfirmed = domain.accountConfirmed,
                accounts = domain.accounts?.map { socialAccountDtoMapper.toDto(it) },
                email = domain.email,
                password = domain.password,
                github = domain.github,
                role = domain.role,
                projects = domain.projects?.map { userProjectDtoMapper.toDto(it) },
                phoneNumber = domain.phoneNumber,
                photoUrl = domain.photoUrl,
                skills = domain.skills?.map { userSkillDtoMapper.toDto(it) },
                notificationPreferences = domain.notificationPreferences
            )

}