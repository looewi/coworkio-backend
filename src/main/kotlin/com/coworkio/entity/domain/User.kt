package com.coworkio.entity.domain

import com.coworkio.entity.domain.enum.NotificationPreferences
import com.coworkio.entity.domain.enum.Role
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "user")
data class User(
        var baseInfo: BaseInfo,

        @Field(value = "first_name")
        var firstName: String,

        @Field(value = "middle_name")
        var middleName: String?,

        @Field(value = "last_name")
        var lastName: String,

        var role: Role = Role.STUDENT,
        var email: String,
        var password: String,

        @Field(value = "phone_number")
        var phoneNumber: String?,

        var accounts: List<SocialAccount>?,
        var github: String?,

        @Field(value = "photo_url")
        var photoUrl: String?,

        var university: UniversityInfo?,

        var skills: List<UserSkill>?,
        var projects: List<UserProject>?,

        @Field(value = "notification_preferences")
        var notificationPreferences: NotificationPreferences = NotificationPreferences.ALL
)