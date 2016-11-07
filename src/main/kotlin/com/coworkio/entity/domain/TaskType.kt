package com.coworkio.entity.domain

enum class TaskType(var value: String) {
    DEVELOPMENT("development"),
    BUG("big"),
    COSMETICS("cosmetics"),
    WONTFIX("wontfix"),
    ASSUMPTION("assumption")
}