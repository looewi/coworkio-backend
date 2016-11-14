package com.coworkio.dto

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.validation.annotation.Validated
import java.io.Serializable
import java.util.*

@Validated
open class ProjectDto: Serializable {

    var id: String? = null

    @NotEmpty
    lateinit var title: String

    var description: String? = ""

    var startDate: Date = Date()

    var endDate: Date? = null

    var positions: List<PositionDto>? = null

//    TODO: will be added as incoming from frontend data later
//    lateinit var sprints: List<SprintDto>

//    TODO: will be added as incoming from frontend data later
//    lateinit var board: BoardDto

    var githubLink: String? = null

    constructor()

    constructor(id: String?, title: String, description: String?, startDate: Date, endDate: Date?, positions: List<PositionDto>?, githubLink: String?) {
        this.id = id
        this.title = title
        this.description = description
        this.startDate = startDate
        this.endDate = endDate
        this.positions = positions
        this.githubLink = githubLink
    }
}