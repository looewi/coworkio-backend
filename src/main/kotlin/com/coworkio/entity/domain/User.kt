package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field

data class User(
        var baseInfo: BaseInfo,

        @Field(value = "first_name")
        var firstName: String,

        @Field(value = "middle_name")
        var middleName: String,

        @Field(value = "last_name")
        var lastName: String,

        var role: Role,
        var email: String,
        var password: String,

        @Field(value = "phone_number")
        var phoneNumber: String,

        var accounts: List<Account>,
        var github: String,
        var photoUrl: String?,

        var List<>

        )