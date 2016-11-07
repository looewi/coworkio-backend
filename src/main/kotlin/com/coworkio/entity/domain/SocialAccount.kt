package com.coworkio.entity.domain

data class SocialAccount (
        var type: SocialAccountType = SocialAccountType.OTHER,
        var link: String
)