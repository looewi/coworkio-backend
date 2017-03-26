package com.coworkio.entity.domain.enum

enum class Priority(val level: Int, val value: String) {
    LOW(0, "LOW"),
    MINOR(1, "MINOR"),
    NORMAL(2, "NORMAL"),
    MAJOR(3, "MAJOR"),
    BLOCKER(4, "BLOCKER");

    fun getHigherPriority() = getPriorityByLevel(this.level + 1)

    fun getLowerPriority() = getPriorityByLevel(this.level - 1)

    fun getPriorityByLevel(level: Int) = Priority.values().firstOrNull { it.level == level }
}