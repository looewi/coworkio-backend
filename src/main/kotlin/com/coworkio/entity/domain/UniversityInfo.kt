package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field

data class UniversityInfo (
        var university: String?,
        var faculty: String?,
        var department: String?,
        var group: String?,

        @Field(value = "start_year")
        var startYear: Int?,

        @Field(value = "end_year")
        var endYear: Int?
)