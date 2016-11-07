package com.coworkio.entity.domain

data class PositionInfo (

        var title: String,
        var description: String?,
        var type: PositionType = PositionType.OTHER
)