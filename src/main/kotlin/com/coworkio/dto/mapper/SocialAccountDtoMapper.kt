package com.coworkio.dto.mapper

import com.coworkio.dto.SocialAccountDto
import com.coworkio.entity.domain.SocialAccount
import org.springframework.stereotype.Component

@Component
open class SocialAccountDtoMapper: DtoMapper<SocialAccount, SocialAccountDto> {

    override fun toDomain(dto: SocialAccountDto)
            = SocialAccount(
                type = dto.type,
                link = dto.link
            )

    override fun toDto(domain: SocialAccount)
            = SocialAccountDto(
                type = domain.type,
                link = domain.link
            )
}