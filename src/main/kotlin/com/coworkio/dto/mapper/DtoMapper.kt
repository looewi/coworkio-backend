package com.coworkio.dto.mapper

interface DtoMapper<DOMAIN, DTO> {

    fun toDomain(dto: DTO): DOMAIN
    fun toDto(domain: DOMAIN): DTO

}