package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable

data class UserSkill (
        val skillId: String,

        @Field(value = "skill_level")
        val skillLevel: Int,

        val approvers: MutableList<String>
):Serializable