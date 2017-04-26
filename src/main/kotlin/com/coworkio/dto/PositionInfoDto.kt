package com.coworkio.dto

import org.springframework.validation.annotation.Validated
import java.io.Serializable
import javax.validation.constraints.NotNull

@Validated
open class PositionInfoDto: Serializable {

    @NotNull
    lateinit var title: String

    var description: String? = null

    var type: String = ""

    constructor()

    constructor(title: String, description: String?, type: String) {
        this.title = title
        this.description = description
        this.type = type
    }
}