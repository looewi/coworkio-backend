package com.coworkio.dto

import com.coworkio.entity.domain.enum.PositionType
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.validation.annotation.Validated
import java.io.Serializable
import java.util.*

@Validated
open class PositionDto: Serializable {

    var id: String = Date().time.toString()

    @NotEmpty
    var title: String = ""

    var description: String? = ""

//    TODO: now is default to FULLSTACK, will be configurable later
    var type: PositionType = PositionType.FULLSTACK

//    TODO: now is not used at all, set to null in the db, will be configurable later
//    var requiredSkills: List<SkillRequirement>?

    var employeeId: String? = null

//    TODO: now is not used at all, set to null in the db, will be not-null later
//    var positionRequests: List<PositionRequest>?

    constructor()

    constructor(id: String, title: String, description: String?, type: PositionType, employeeId: String?) {
        this.id = id
        this.title = title
        this.description = description
        this.type = type
        this.employeeId = employeeId
    }
}