package com.coworkio.dto.mapper

import com.coworkio.dto.UserSkillDto
import com.coworkio.entity.domain.UserSkill
import org.springframework.stereotype.Component

@Component
open class UserSkillDtoMapper: DtoMapper<UserSkill, UserSkillDto> {

    override fun toDomain(dto: UserSkillDto)
            = UserSkill(
                skillId = dto.skillId,
                skillLevel = dto.skillLevel,
                approvers = dto.approvers
            )

    override fun toDto(domain: UserSkill)
            = UserSkillDto(
                skillId = domain.skillId,
                skillLevel = domain.skillLevel,
                approvers = domain.approvers
            )
}