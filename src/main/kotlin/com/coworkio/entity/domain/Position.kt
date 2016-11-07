package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field

data class Position (
        var title: String,
        var description: String?,

        @Field(value = "required_skills")
        var requiredSkillRequirements: List<SkillRequirement>?,

        var type: PositionType = PositionType.OTHER,

        @Field(value = "employee_id")
        var employeeId: String?,

        @Field(value = "position_requests")
        var positionRequests: List<PositionRequest>?
)