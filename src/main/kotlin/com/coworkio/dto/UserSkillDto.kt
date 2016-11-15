package com.coworkio.dto

import org.springframework.validation.annotation.Validated
import java.io.Serializable

@Validated
open class UserSkillDto: Serializable {

    lateinit var skillId: String

    var skillLevel: Int = 10

    var approvers: List<String>? = null

    constructor()

    constructor(skillId: String, skillLevel: Int, approvers: List<String>?) {
        this.skillId = skillId
        this.skillLevel = skillLevel
        this.approvers = approvers
    }
}