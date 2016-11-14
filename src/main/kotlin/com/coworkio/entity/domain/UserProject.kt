package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable
import java.util.*

data class UserProject(

        @Field(value = "project_id")
        var projectId: String,

        var positionInfo: PositionInfo,

        @Field(value = "is_current")
        var isCurrent: Boolean,

        @Field(value = "start_date")
        var startDate: Date?,

        @Field(value = "end_date")
        var endDate: Date?
):Serializable