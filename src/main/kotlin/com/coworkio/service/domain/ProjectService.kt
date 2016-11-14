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


    fun saveOrUpdate(projectDto: ProjectDto): Project {
        val project = projectDtoMapper.toDomain(projectDto)
        return saveOrUpdate(project)
    }

    fun saveOrUpdate(project: Project)
            = if(project.id != null) {
                projectRepository.save(project)
            } else {
                projectRepository.insert(project)
            }

    fun getProjectDtoById(id: String): ProjectDto? {
        val project = projectRepository.findOne(id)
        return if(project != null) {
            projectDtoMapper.toDto(project)
        }  else {
            null
        }
    }

    fun getProjectById(id: String): Project? {
        val project = projectRepository.findOne(id)
        return project
    }

    fun getProjectsByUser(userId: String): List<ProjectDto>? {
        throw NotImplementedError("method is not implemented yet")
    }

    fun removeSilently(id: String): Boolean {
        val project = projectRepository.findOne(id)
        return if(project != null) {
            project.baseInfo = BaseInfo(Date(), true)
            projectRepository.save(project)
            true
        } else {
            false
        }
    }

    fun removeSilently(projectDto: ProjectDto): Boolean {
        val project = projectDtoMapper.toDomain(projectDto)
        project.baseInfo = BaseInfo(Date(), true)
        projectRepository.save(project)
        return true
    }

    fun delete(id: String) {
        projectRepository.delete(id)
    }

    fun addPosition() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}