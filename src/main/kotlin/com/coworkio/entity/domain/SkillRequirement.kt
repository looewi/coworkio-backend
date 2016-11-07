package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field

data class SkillRequirement(

        @Field(value = "skill_id")
        var skillId: String?,

        @Field(value = "skill_level")
        var skillLevel: Int = 10
)