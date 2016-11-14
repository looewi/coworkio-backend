package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable

data class SkillRequirement(

        @Field(value = "skill_id")
        var skillId: String?,

        @Field(value = "skill_level")
        var skillLevel: Int
):Serializable