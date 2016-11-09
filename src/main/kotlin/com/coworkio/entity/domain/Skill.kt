package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "skill")
data class Skill (
        var id: String?,

        @Field(value = "base_info")
        var baseInfo: BaseInfo,

        var title: String,

        @Field(value = "total_used_count")
        var totalUsedCount: Int
)