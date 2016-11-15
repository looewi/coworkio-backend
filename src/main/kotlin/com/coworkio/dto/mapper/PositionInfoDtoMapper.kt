package com.coworkio.dto.mapper

import com.coworkio.dto.PositionInfoDto
import com.coworkio.entity.domain.PositionInfo
import org.springframework.stereotype.Component

@Component
open class PositionInfoDtoMapper : DtoMapper<PositionInfo, PositionInfoDto> {

    override fun toDomain(dto: PositionInfoDto)
            = PositionInfo(
                type = dto.type,
                title = dto.title,
                description = dto.description
            )

    override fun toDto(domain: PositionInfo)
            = PositionInfoDto(
                type = domain.type,
                description = domain.description,
                title = domain.title
            )
}