package com.coworkio.dto

import com.coworkio.entity.domain.enum.PositionType
import org.springframework.validation.annotation.Validated
import java.io.Serializable
import javax.validation.constraints.NotNull

@Validated
open class PositionInfoDto: Serializable {

    @NotNull
    lateinit var title: String

    var description: String? = null

    var type: PositionType = PositionType.FULLSTACK

    constructor()

    constructor(title: String, description: String?, type: PositionType) {
        this.title = title
        this.description = description
        this.type = type
    }
}