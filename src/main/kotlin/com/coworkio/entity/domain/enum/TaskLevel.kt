package com.coworkio.entity.domain.enum

enum class TaskLevel(val level: Int, val value: String) {
    EPIC(0, "EPIC"),
    USER_STORY(1, "USER_STORY"),
    TASK(2, "TASK");

    fun getParentType() = getByLevel(this.level - 1)

    fun getChildType() = getByLevel(this.level + 1)

    fun getByLevel(level: Int) = TaskLevel.values().firstOrNull { it.level == level }
}