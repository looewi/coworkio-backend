package com.coworkio.entity.domain

import com.coworkio.entity.domain.enum.PositionType

data class PositionInfo (

        var title: String,
        var description: String?,
        var type: PositionType = PositionType.OTHER
)