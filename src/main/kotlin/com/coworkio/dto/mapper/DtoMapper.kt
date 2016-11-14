package com.coworkio.dto.mapper

import org.springframework.stereotype.Component

@Component
interface DtoMapper<DOMAIN, DTO> {

    fun toDomain(dto: DTO): DOMAIN
    fun toDto(domain: DOMAIN): DTO

}