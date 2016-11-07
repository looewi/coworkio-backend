package com.coworkio.entity.domain

enum class TaskLevel(var level: Int, var value: String) {
    EPIC(0, "epic"),
    USER_STORY(1, "user story"),
    TASK(2, "task");

    open fun getParentType(): TaskLevel? {
        return getByLevel(this.level - 1)
    }

    open fun getChildType(): TaskLevel? {
        return getByLevel(this.level + 1)
    }

    fun getByLevel(level: Int): TaskLevel? {
        for(value in TaskLevel.values()) {
            if(value.level == level) {
                return value
            }
        }
        return null
    }
}