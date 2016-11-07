package com.coworkio.entity.domain

import com.coworkio.entity.domain.enum.SocialAccountType

data class SocialAccount (
        var type: SocialAccountType = SocialAccountType.OTHER,
        var link: String
)