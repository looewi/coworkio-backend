package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable
import java.util.*

data class Sprint(

        @Field(value = "start_date")
        var startDate: Date?,

        @Field(value = "end_date")
        var endDate: Date?,

        @Field(value = "previous_sprint_id")
        var previousSprintId: String?,

        @Field(value = "next_sprint_id")
        var nextSprintId: String?
):Serializable