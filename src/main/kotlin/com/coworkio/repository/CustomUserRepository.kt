package com.coworkio.repository

import com.coworkio.entity.domain.UserProject

interface CustomUserRepository {

    fun addPosition(userProject: UserProject, userEmail: String)
}