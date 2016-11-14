package com.coworkio.dto

import org.springframework.validation.annotation.Validated
import java.io.Serializable

@Validated
open class PositionDto: Serializable {

    var title: String = ""

    var description: String = ""

//    TODO: now is default to DEVELOPER, will be configurable later
//    var type: PositionType

//    TODO: now is not used at all, set to null in the db, will be configurable later
//    var requiredSkills: List<SkillRequirement>?

    var employeeId: String? = null

//    TODO: now is not used at all, set to null in the db, will be not-null later
//    var positionRequests: List<PositionRequest>?
}