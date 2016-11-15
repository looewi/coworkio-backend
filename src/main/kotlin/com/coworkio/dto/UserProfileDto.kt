package com.coworkio.dto

import com.coworkio.entity.domain.enum.NotificationPreferences
import com.coworkio.entity.domain.enum.Role
import org.springframework.validation.annotation.Validated
import java.io.Serializable
import javax.validation.constraints.NotNull

@Validated
open class UserProfileDto: Serializable {

    @NotNull
    lateinit var id: String

    var firstName: String = ""

    var middleName: String? = null

    var lastName: String = ""

    var accountConfirmed: Boolean = true

    var role: Role = Role.STUDENT

    lateinit var email: String
    lateinit var password: String

    var phoneNumber: String? = null

    var accounts: List<SocialAccountDto>? = null

    var github: String? = null

    var photoUrl: String? = null

    var university: UniversityInfoDto? = null

    var skills: List<UserSkillDto>? = null
    var projects: List<UserProjectDto>? = null

    var notificationPreferences: NotificationPreferences = NotificationPreferences.ALL

    constructor()

    constructor(id: String, firstName: String, middleName: String?, lastName: String, accountConfirmed: Boolean, role: Role, email: String, password: String, phoneNumber: String?, accounts: List<SocialAccountDto>?, github: String?, photoUrl: String?, university: UniversityInfoDto?, skills: List<UserSkillDto>?, projects: List<UserProjectDto>?, notificationPreferences: NotificationPreferences) {
        this.id = id
        this.firstName = firstName
        this.middleName = middleName
        this.lastName = lastName
        this.accountConfirmed = accountConfirmed
        this.role = role
        this.email = email
        this.password = password
        this.phoneNumber = phoneNumber
        this.accounts = accounts
        this.github = github
        this.photoUrl = photoUrl
        this.university = university
        this.skills = skills
        this.projects = projects
        this.notificationPreferences = notificationPreferences
    }
}