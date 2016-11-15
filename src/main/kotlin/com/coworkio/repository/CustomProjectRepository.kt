package com.coworkio.repository

import com.coworkio.entity.domain.Position
import com.coworkio.entity.domain.Project
import org.springframework.stereotype.Repository

@Repository
interface CustomProjectRepository {

    fun addPosition(projectId: String, position: Position): String?
    fun updatePosition(projectId: String, position: Position)
    fun getProjectsByIds(projectIds: List<String>): List<Project>
}