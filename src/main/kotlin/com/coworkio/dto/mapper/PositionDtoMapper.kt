package com.coworkio.dto.mapper

import com.coworkio.dto.PositionDto
import com.coworkio.entity.domain.Position
import com.coworkio.entity.domain.PositionInfo
import org.springframework.stereotype.Component

@Component
open class PositionDtoMapper: DtoMapper<Position, PositionDto> {

    override fun toDomain(dto: PositionDto)
            = Position(
                id = dto.id,
                employeeId = dto.employeeId,
                positionInfo = PositionInfo(
                        title = dto.title,
                        description = dto.description,
                        type = dto.type
                ),
                positionRequests = null,
                requiredSkillRequirements = null
            )

    override fun toDto(domain: Position)
            = PositionDto(
                id = domain.id,
                title = domain.positionInfo.title,
                description = domain.positionInfo.description,
                type = domain.positionInfo.type,
                employeeId = domain.employeeId
            )

}

