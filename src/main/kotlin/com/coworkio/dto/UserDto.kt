package com.coworkio.dto

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.validation.annotation.Validated
import java.io.Serializable
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Validated
open class UserDto: Serializable {

    var id:String? = null

    @NotEmpty
    @Email
    lateinit var email: String

    @Pattern(regexp = "(?=^.{8,}$)(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z!@#$%^&*()]*$")
    lateinit var password: String

    @Size(max = 30)
    var lastName: String = ""

    @Size(max = 30)
    var firstName: String = ""

    constructor()

    constructor(email: String, password: String, firstName: String, lastName: String)
}