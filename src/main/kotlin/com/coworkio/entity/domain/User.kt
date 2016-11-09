package com.coworkio.entity.domain

import com.coworkio.entity.domain.enum.NotificationPreferences
import com.coworkio.entity.domain.enum.Role
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "user")
data class User(
        var id: String? = null,

        @Field(value = "base_info")
        var baseInfo: BaseInfo,

        @Field(value = "first_name")
        var firstName: String,

        @Field(value = "middle_name")
        var middleName: String? = null,

        @Field(value = "last_name")
        var lastName: String,

        @Field(value = "account_confirmed")
        var accountConfirmed: Boolean,

        var role: Role = Role.STUDENT,
        var email: String,
        var password: String,

        @Field(value = "phone_number")
        var phoneNumber: String? = null,

        var accounts: List<SocialAccount>? = null,
        var github: String? = null,

        @Field(value = "photo_url")
        var photoUrl: String? = null,

        var university: UniversityInfo? = null,

        var skills: List<UserSkill>? = null,
        var projects: List<UserProject>? = null,

        @Field(value = "notification_preferences")
        var notificationPreferences: NotificationPreferences = NotificationPreferences.ALL
)