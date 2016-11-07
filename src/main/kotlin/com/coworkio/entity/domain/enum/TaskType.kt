package com.coworkio.entity.domain.enum

enum class TaskType(val value: String) {
    DEVELOPMENT("development"),
    BUG("big"),
    COSMETICS("cosmetics"),
    WONTFIX("wontfix"),
    ASSUMPTION("assumption")
}