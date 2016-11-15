package com.coworkio.dto

import org.springframework.validation.annotation.Validated
import java.io.Serializable

@Validated
open class UniversityInfoDto: Serializable {

    var university: String? = null

    var faculty: String? = null

    var department: String? = null

    var group: String? = null

    var startYear: Int? = null

    var endYear: Int? = null

    constructor()

    constructor(university: String?, faculty: String?, department: String?, group: String?, startYear: Int?, endYear: Int?) {
        this.university = university
        this.faculty = faculty
        this.department = department
        this.group = group
        this.startYear = startYear
        this.endYear = endYear
    }
}