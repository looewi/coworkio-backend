package com.coworkio.dto.mapper

import com.coworkio.dto.UniversityInfoDto
import com.coworkio.entity.domain.UniversityInfo
import org.springframework.stereotype.Component

@Component
open class UniversityDtoMapper: DtoMapper<UniversityInfo, UniversityInfoDto> {

    override fun toDomain(dto: UniversityInfoDto)
            = UniversityInfo(
                faculty = dto.faculty,
                department = dto.department,
                university = dto.university,
                startYear = dto.startYear,
                endYear = dto.endYear,
                group = dto.group
            )

    override fun toDto(domain: UniversityInfo)
            = UniversityInfoDto(
                faculty = domain.faculty,
                department = domain.department,
                university = domain.university,
                startYear = domain.startYear,
                endYear = domain.endYear,
                group = domain.group
            )
}