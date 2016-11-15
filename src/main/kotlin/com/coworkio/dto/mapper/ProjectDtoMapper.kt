package com.coworkio.dto.mapper

import com.coworkio.dto.PositionDto
import com.coworkio.dto.ProjectDto
import com.coworkio.entity.domain.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
open class ProjectDtoMapper : DtoMapper<Project, ProjectDto>{

    @Autowired
    private lateinit var positionDtoMapper: PositionDtoMapper

    override fun toDomain(dto: ProjectDto)
            = Project(
                id = dto.id,
                baseInfo = BaseInfo(Date(), false),
                title = dto.title,
                description = dto.description,
                startDate = dto.startDate,
                endDate = dto.endDate,
                positions = dto.positions?.map {
                    it -> positionDtoMapper.toDomain(it)
                },
                sprints = defaultSprint(),
                board = defaultBoard(),
                githubLink = dto.githubLink
            )

    override fun toDto(domain: Project)
            = ProjectDto(
            id = domain.id,
            title = domain.title,
            description = domain.description,
            startDate = domain.startDate,
            endDate = domain.endDate,
            positions = domain.positions?.map {
                it -> positionDtoMapper.toDto(it)
            },
            githubLink = domain.githubLink
    )

    private fun defaultBoard()
            = Board(
                statuses = listOf(
                    Status(1, "New"),
                    Status(2, "Reopened"),
                    Status(3, "In progress"),
                    Status(4, "In review"),
                    Status(5, "Closed")
                ),
                workingHoursPerDay = 4,
                workingDaysPerWeek = 5
            )

    private fun defaultSprint()
            = listOf(
                Sprint(
                    startDate = Date(),
                    endDate = null,
                    previousSprintId = null,
                    nextSprintId = null
                )
            )
}