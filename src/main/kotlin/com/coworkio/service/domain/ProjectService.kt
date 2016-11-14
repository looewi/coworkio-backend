package com.coworkio.service.domain

import com.coworkio.dto.ProjectDto
import com.coworkio.dto.mapper.ProjectDtoMapper
import com.coworkio.entity.domain.BaseInfo
import com.coworkio.entity.domain.Project
import com.coworkio.repository.ProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
open class ProjectService {

    @Autowired
    lateinit private var projectDtoMapper: ProjectDtoMapper

    @Autowired
    lateinit private var projectRepository: ProjectRepository


    open fun saveOrUpdate(projectDto: ProjectDto): Project {
        val project = projectDtoMapper.toDomain(projectDto)
        return if(project.id != null) {
            projectRepository.save(project)
        } else {
            projectRepository.insert(project)
        }
    }

    open fun getProjectById(id: String): ProjectDto? {
        val project = projectRepository.findOne(id)
        return if(project != null) {
            projectDtoMapper.toDto(project)
        }  else {
            null
        }
    }

    open fun getProjectsByUser(userId: String): List<ProjectDto>? {
        throw NotImplementedError("method is not implemented yet")
    }

    open fun removeProject(id: String): Boolean {
        val project = projectRepository.findOne(id)
        return if(project != null) {
            project.baseInfo = BaseInfo(Date(), true)
            projectRepository.save(project)
            true
        } else {
            false
        }
    }

    open fun removeProject(projectDto: ProjectDto): Boolean {
        val project = projectDtoMapper.toDomain(projectDto)
        project.baseInfo = BaseInfo(Date(), true)
        projectRepository.save(project)
        return true
    }
}