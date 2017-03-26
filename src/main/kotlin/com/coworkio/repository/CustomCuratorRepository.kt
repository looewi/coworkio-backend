package com.coworkio.repository

import com.coworkio.entity.domain.Curator

interface CustomCuratorRepository {

    fun findAllCuratorsByUniversityInfo(params: Map<String, String?>): List<Curator>
}