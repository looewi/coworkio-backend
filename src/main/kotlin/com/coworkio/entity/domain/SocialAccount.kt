package com.coworkio.entity.domain

import com.coworkio.entity.domain.enum.SocialAccountType
import java.io.Serializable

data class SocialAccount (
        var type: SocialAccountType,
        var link: String
):Serializable