package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable

data class Board (
        var statuses: List<Status>,

        @Field(value = "working_hours_per_day")
        var workingHoursPerDay: Int,

        @Field(value = "working_days_per_week")
        var workingDaysPerWeek: Int
):Serializable