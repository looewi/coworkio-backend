package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document(collection = "sprint")
data class Sprint(

        var baseInfo: BaseInfo,

        @Field(value = "start_date")
        var startDate: Date?,

        @Field(value = "end_date")
        var endDate: Date?,

        @Field(value = "previous_sprint_id")
        var previousSprintId: String?,

        @Field(value = "next_sprint_id")
        var nextSprintId: String?
)