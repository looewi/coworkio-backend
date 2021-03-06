package com.coworkio.repository

import com.coworkio.entity.domain.User
import com.coworkio.entity.domain.UserProject

interface CustomUserRepository {

    fun addPosition(userProject: UserProject, userEmail: String)
    fun findUsers(firstName: String?, lastName: String?, university: String?, faculty: String?) : List<User>?
}