package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable
import java.util.*

data class BaseInfo (

        @Field(value = "last_modified_date")
        var lastModifiedDate: Date,

        @Field(value = "is_deleted")
        var isDeleted:Boolean
):Serializable