package com.coworkio.dto

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.validation.annotation.Validated
import java.io.Serializable
import java.util.*

@Validated
open class NewProjectDto(
        val id: String? = null,
        @NotEmpty val title: String = "",
        val description: String? = null,
        val startDate: Date? = null,
        val endDate: Date? = null,
        val positions: List<PositionDto>? = null,
        val githubLink: String? = null
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NewProjectDto) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (startDate != other.startDate) return false
        if (endDate != other.endDate) return false
        if (positions != other.positions) return false
        if (githubLink != other.githubLink) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + title.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (startDate?.hashCode() ?: 0)
        result = 31 * result + (endDate?.hashCode() ?: 0)
        result = 31 * result + (positions?.hashCode() ?: 0)
        result = 31 * result + (githubLink?.hashCode() ?: 0)
        return result
    }
}

/*
Board, sprints and position entities are not passed from the client side
and are generated automatically at the moment of creation such an object
 */