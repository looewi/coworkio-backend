package com.coworkio.entity.domain

data class Curator (
        val id: String? = null,
        val name: String,
        val surname: String,
        val universityInfo: UniversityInfo,
        val maxProjects: Int,
        val projects: List<String>?
)