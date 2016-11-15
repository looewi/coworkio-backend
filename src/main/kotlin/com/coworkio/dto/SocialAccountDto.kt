package com.coworkio.dto

import com.coworkio.entity.domain.enum.SocialAccountType
import org.springframework.validation.annotation.Validated
import java.io.Serializable

@Validated
open class SocialAccountDto: Serializable {

    lateinit var type: SocialAccountType
    lateinit var link: String

    constructor()

    constructor(type: SocialAccountType, link: String) {
        this.type = type
        this.link = link
    }
}