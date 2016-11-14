package com.coworkio.entity.domain

import com.coworkio.entity.domain.enum.PositionRequestStatus
import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable
import java.util.*

data class PositionRequest (

        @Field(value = "employee_id")
        var employeeId: String,

        @Field(value = "employer_id")
        var employerId: String,

        var comment: String?,

        @Field(value = "status")
        var positionRequestStatus: PositionRequestStatus,

        @Field(value = "created_date")
        var createdDate: Date
):Serializable