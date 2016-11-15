package com.coworkio.dto

import org.springframework.validation.annotation.Validated
import java.io.Serializable
import java.util.*
import javax.validation.constraints.NotNull

@Validated
open class UserProjectDto: Serializable {

    @NotNull
    lateinit var projectId: String

    lateinit var positionInfo: PositionInfoDto

    @NotNull
    var isCurrent: Boolean = true

    var startDate: Date? = null

    var endDate: Date? = null

    constructor()

    constructor(projectId: String, positionInfo: PositionInfoDto, isCurrent: Boolean, startDate: Date?, endDate: Date?) {
        this.projectId = projectId
        this.positionInfo = positionInfo
        this.isCurrent = isCurrent
        this.startDate = startDate
        this.endDate = endDate
    }
}