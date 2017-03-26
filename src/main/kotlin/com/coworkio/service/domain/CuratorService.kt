package com.coworkio.service.domain

import com.coworkio.entity.domain.Curator
import com.coworkio.repository.CuratorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class CuratorService(
        @Autowired val curatorRepository: CuratorRepository) {

    fun getAllCurators(properties: Map<String, String?>): List<Curator> {
        val props = properties.filter { it.value != null }
        return when(props.size) {
            0 -> curatorRepository.findAll()
            else -> curatorRepository.findAllCuratorsByUniversityInfo(props)
        }
    }

    fun addProject(curatorId: String, projectId: String) {
        curatorRepository.addProject(curatorId, projectId)
    }
}