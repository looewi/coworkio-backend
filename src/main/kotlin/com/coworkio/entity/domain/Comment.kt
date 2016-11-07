package com.coworkio.entity.domain

import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

data class Comment(
        var text: String,

        @Field(value = "author_id")
        var authorId: String,

        @Field(value = "created_date")
        var createdDate: Date = Date(),

        @Field(value = "modified_date")
        var modifiedDate: Date?
)