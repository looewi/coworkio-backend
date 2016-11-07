package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

data class BaseInfo (

        var id: String?,

        @Field(value = "last_modified_date")
        var lastModifiedDate: Date = Date(),

        @Field(value = "is_deleted")
        var isDeleted:Boolean = false
)