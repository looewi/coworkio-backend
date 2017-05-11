package com.coworkio.dto

import com.coworkio.entity.domain.User
import java.io.Serializable

open class UserMinDto: Serializable {

    var id:String? = null

    var lastName: String = ""

    var firstName: String = ""

    var photoUrl: String? = ""

    constructor()

    constructor(id: String?, firstName: String, lastName: String, photoUrl: String?)
}

fun User.toUserMinDto() = UserMinDto(this.id, this.firstName, this.lastName, this.photoUrl)