package com.coworkio.dto.mapper

import com.coworkio.dto.ProjectMinifiedVersionDto
import com.coworkio.entity.domain.Project
import org.springframework.stereotype.Component

@Component
open class ProjectMinifiedVersionDtoMapper: DtoMapper<Project, ProjectMinifiedVersionDto> {

    override fun toDomain(dto: ProjectMinifiedVersionDto): Project {
        throw UnsupportedOperationException("conversion is not allowed")
    }

    override fun toDto(domain: Project)
            = ProjectMinifiedVersionDto(
                id = domain.id,
                title = domain.title,
                description = domain.description,
                startDate = domain.startDate,
                endDate = domain.endDate,
                githubLink = domain.githubLink
            )
}