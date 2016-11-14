package com.coworkio.entity.domain

import com.coworkio.entity.domain.enum.NotificationPreferences
import com.coworkio.entity.domain.enum.Role
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable

@Document(collection = "user")
data class User(
        var id: String?,

        @Field(value = "base_info")
        var baseInfo: BaseInfo,

        @Field(value = "first_name")
        var firstName: String,

        @Field(value = "middle_name")
        var middleName: String?,

        @Field(value = "last_name")
        var lastName: String,

        @Field(value = "account_confirmed")
        var accountConfirmed: Boolean,

        var role: Role,
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
        var notificationPreferences: NotificationPreferences
): Serializable