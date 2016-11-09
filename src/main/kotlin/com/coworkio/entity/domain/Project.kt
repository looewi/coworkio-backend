package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document(collection = "project")
data class Project(
        var id: String?,

        @Field(value = "base_info")
        var baseInfo: BaseInfo,

        var title: String,
        var description: String?,

        @Field(value = "start_date")
        var startDate: Date,

        @Field(value = "end_date")
        var endDate: Date,

        var positions: List<Position>?,
        var sprints: List<Sprint>?,
        var board: Board,

        @Field(value = "github_link")
        var githubLink: String?
)