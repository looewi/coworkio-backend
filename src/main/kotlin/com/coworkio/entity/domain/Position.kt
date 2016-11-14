package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable

data class Position (
        var positionInfo: PositionInfo,

        @Field(value = "required_skills")
        var requiredSkillRequirements: List<SkillRequirement>?,

        @Field(value = "employee_id")
        var employeeId: String?,

        @Field(value = "position_requests")
        var positionRequests: List<PositionRequest>?
):Serializable