package com.coworkio.dto.mapper

import com.coworkio.dto.UserProfileDto
import com.coworkio.entity.domain.BaseInfo
import com.coworkio.entity.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
open class UserProfileDtoMapper: DtoMapper<User, UserProfileDto> {

    @Autowired
    private lateinit var positionInfoDtoMapper: PositionInfoDtoMapper

    @Autowired
    private lateinit var socialAccountDtoMapper: SocialAccountDtoMapper

    @Autowired
    private lateinit var universityDtoMapper: UniversityDtoMapper

    @Autowired
    private lateinit var userProjectDtoMapper: UserProjectDtoMapper

    @Autowired
    private lateinit var userSkillDtoMapper: UserSkillDtoMapper

    override fun toDomain(dto: UserProfileDto)
            = User(
                id = dto.id,
                baseInfo = BaseInfo(Date(), true),
                firstName = dto.firstName,
                middleName = dto.middleName,
                lastName = dto.lastName,
                university = dto.university.let {
                    it -> if(it != null) {
                            universityDtoMapper.toDomain(it)
                        } else {
                            null
                        }
                },
                accountConfirmed = dto.accountConfirmed,
                accounts = dto.accounts?.map {
                    it -> socialAccountDtoMapper.toDomain(it)
                },
                email = dto.email,
                password = dto.password,
                github = dto.github,
                role = dto.role,
                projects = dto.projects?.map {
                    it -> userProjectDtoMapper.toDomain(it)
                },
                phoneNumber = dto.phoneNumber,
                photoUrl = dto.photoUrl,
                skills = dto.skills?.map {
                    it -> userSkillDtoMapper.toDomain(it)
                },
                notificationPreferences = dto.notificationPreferences
    )

    override fun toDto(domain: User)
            = UserProfileDto(
                id = domain.id!!,
                firstName = domain.firstName,
                middleName = domain.middleName,
                lastName = domain.lastName,
                university = domain.university.let {
                    it -> if(it != null) {
                        universityDtoMapper.toDto(it)
                    } else {
                        null
                    }
                },
                accountConfirmed = domain.accountConfirmed,
                accounts = domain.accounts?.map {
                    it -> socialAccountDtoMapper.toDto(it)
                },
                email = domain.email,
                password = domain.password,
                github = domain.github,
                role = domain.role,
                projects = domain.projects?.map {
                    it -> userProjectDtoMapper.toDto(it)
                },
                phoneNumber = domain.phoneNumber,
                photoUrl = domain.photoUrl,
                skills = domain.skills?.map {
                    it -> userSkillDtoMapper.toDto(it)
                },
                notificationPreferences = domain.notificationPreferences
            )

}