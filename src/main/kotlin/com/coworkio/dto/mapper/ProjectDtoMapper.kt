package com.coworkio.dto.mapper

import com.coworkio.dto.NewProjectDto
import com.coworkio.entity.domain.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
open class ProjectDtoMapper : DtoMapper<Project, NewProjectDto>{

    @Autowired
    private lateinit var positionDtoMapper: PositionDtoMapper

    override fun toDomain(dto: NewProjectDto)
            = Project(
                id = dto.id,
                baseInfo = BaseInfo(Date(), false),
                title = dto.title,
                description = dto.description,
                startDate = dto.startDate ?: Date(),
                endDate = dto.endDate,
                positions = dto.positions?.map {positionDtoMapper.toDomain(it)},
                sprints = defaultSprint(),
                board = defaultBoard(),
                githubLink = dto.githubLink
            )

    override fun toDto(domain: Project)
            = NewProjectDto(
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
                    Status(1, "Новая"),
                    Status(2, "Переоткрытая"),
                    Status(3, "Выполняется"),
                    Status(4, "На обзоре"),
                    Status(5, "Закрыта")
                ),
                workingHoursPerDay = 4,
                workingDaysPerWeek = 5
            )

    private fun defaultSprint()
            = listOf(
                Sprint(
                    id = 0,
                    startDate = Date(),
                    endDate = null,
                    previousSprintId = null,
                    nextSprintId = null
                )
            )
}