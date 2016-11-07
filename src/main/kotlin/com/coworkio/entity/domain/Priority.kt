package com.coworkio.entity.domain

enum class Priority(var level: Int, var value: String) {
    LOW(0, "low"),
    MINOR(1, "minor"),
    NORMAL(2, "normal"),
    MAJOR(3, "major"),
    BLOCKER(4, "blocker");

    open fun getHigherPriority(): Priority? {
        return getPriorityByLevel(this.level + 1)
    }

    open fun getLowerPriority(): Priority? {
        return getPriorityByLevel(this.level - 1)
    }

    fun getPriorityByLevel(level: Int): Priority? {
        for (value in Priority.values()) {
            if(value.level == level) {
                return value
            }
        }
        return null
    }
}