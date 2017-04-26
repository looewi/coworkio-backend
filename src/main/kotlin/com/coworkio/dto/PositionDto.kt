package com.coworkio.dto

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
    var type: String = "FULLSTACK"

//    TODO: now is not used at all, set to null in the db, will be configurable later
//    var requiredSkills: List<SkillRequirement>?

    var employeeId: String? = null

//    TODO: now is not used at all, set to null in the db, will be not-null later
//    var positionRequests: List<PositionRequest>?

    constructor()

    constructor(id: String, title: String, description: String?, type: String, employeeId: String?) {
        this.id = id
        this.title = title
        this.description = description
        this.type = type
        this.employeeId = employeeId
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PositionDto) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (type != other.type) return false
        if (employeeId != other.employeeId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + type.hashCode()
        result = 31 * result + (employeeId?.hashCode() ?: 0)
        return result
    }


}