package com.coworkio.repository

import com.coworkio.entity.domain.Position
import org.springframework.stereotype.Repository

@Repository
interface CustomProjectRepository {

    fun addPosition(projectId: String, position: Position): String?
    fun updatePosition(projectId: String, position: Position)
}