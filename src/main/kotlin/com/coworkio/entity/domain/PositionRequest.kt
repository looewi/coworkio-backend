package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

data class PositionRequest (

        @Field(value = "employee_id")
        var employeeId: String,

        @Field(value = "employer_id")
        var employerId: String,

        var comment: String?,

        @Field(value = "status")
        var positionRequestStatus: PositionRequestStatus = PositionRequestStatus.CREATED,

        @Field(value = "created_date")
        var createdDate: Date
)