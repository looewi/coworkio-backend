package com.coworkio.entity.domain.enum

enum class TaskLevel(val level: Int, val value: String) {
    EPIC(0, "EPIC"),
    USER_STORY(1, "USER_STORY"),
    TASK(2, "TASK");

    open fun getParentType(): TaskLevel? {
        return getByLevel(this.level - 1)
    }

    open fun getChildType(): TaskLevel? {
        return getByLevel(this.level + 1)
    }

    fun getByLevel(level: Int): TaskLevel?
            = TaskLevel.values().firstOrNull { it.level == level }
}