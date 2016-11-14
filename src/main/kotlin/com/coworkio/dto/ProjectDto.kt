package com.coworkio.dto

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.validation.annotation.Validated
import java.io.Serializable
import java.util.*

@Validated
open class ProjectDto: Serializable {

    @NotEmpty
    lateinit var title: String

    var description: String? = ""

    var startDate: Date? = Date()

    var endDate: Date? = null

    var positions: List<PositionDto>? = null

//    TODO: will be added as incoming from frontend data later
//    lateinit var sprints: List<SprintDto>

//    TODO: will be added as incoming from frontend data later
//    lateinit var board: BoardDto

    lateinit var githubLink: String

}