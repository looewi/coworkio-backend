package com.coworkio.entity.domain.enum

enum class Priority(val level: Int, val value: String) {
    LOW(0, "LOW"),
    MINOR(1, "MINOR"),
    NORMAL(2, "NORMAL"),
    MAJOR(3, "MAJOR"),
    BLOCKER(4, "BLOCKER");

    open fun getHigherPriority(): Priority? {
        return getPriorityByLevel(this.level + 1)
    }

    open fun getLowerPriority(): Priority? {
        return getPriorityByLevel(this.level - 1)
    }

    fun getPriorityByLevel(level: Int): Priority?
            = Priority.values().firstOrNull { it.level == level }
}