package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field

data class Board (
        var statuses: List<Status> = listOf(Status(0, "Created")),

        @Field(value = "working_hours_per_day")
        var workingHoursPerDay: Int = 8,

        @Field(value = "working_days_per_week")
        var woringDaysPerWeek: Int = 5
)