package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable

data class UserSkill (
        var skillId: String,

        @Field(value = "skill_level")
        var skillLevel: Int,

        var approvers: List<String>?
):Serializable