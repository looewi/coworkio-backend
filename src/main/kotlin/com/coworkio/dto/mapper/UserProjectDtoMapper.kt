package com.coworkio.dto.mapper

import com.coworkio.dto.UserProjectDto
import com.coworkio.entity.domain.Position
import com.coworkio.entity.domain.PositionInfo
import com.coworkio.entity.domain.UserProject
import com.coworkio.entity.domain.enum.PositionType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class UserProjectDtoMapper: DtoMapper<UserProject, UserProjectDto> {

    @Autowired
    private lateinit var positionInfoDtoMapper: PositionInfoDtoMapper

    override fun toDomain(dto: UserProjectDto)
            = UserProject(
                projectId = dto.projectId,
                isCurrent = dto.isCurrent,
                startDate = dto.startDate,
                endDate = dto.endDate,
                positionInfo = positionInfoDtoMapper.toDomain(dto.positionInfo)
    )

    override fun toDto(domain: UserProject)
            = UserProjectDto(
                projectId = domain.projectId,
                isCurrent = domain.isCurrent,
                startDate = domain.startDate,
                endDate = domain.endDate,
                positionInfo = positionInfoDtoMapper.toDto(domain.positionInfo)
    )
}