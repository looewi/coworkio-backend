package com.coworkio.service.domain

import com.coworkio.dto.PositionDto
import com.coworkio.dto.ProjectDto
import com.coworkio.dto.ProjectMinifiedVersionDto
import com.coworkio.dto.mapper.PositionDtoMapper
import com.coworkio.dto.mapper.ProjectDtoMapper
import com.coworkio.dto.mapper.ProjectMinifiedVersionDtoMapper
import com.coworkio.entity.domain.BaseInfo
import com.coworkio.entity.domain.Project
import com.coworkio.repository.ProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
open class ProjectService(
        @Autowired val projectDtoMapper: ProjectDtoMapper,
        @Autowired val projectMinifiedVersionMapper: ProjectMinifiedVersionDtoMapper,
        @Autowired val positionDtoMapper: PositionDtoMapper,
        @Autowired val projectRepository: ProjectRepository) {

    fun saveOrUpdate(projectDto: ProjectDto) : Project
            = saveOrUpdate(projectDtoMapper.toDomain(projectDto))

    fun saveOrUpdate(project: Project) : Project
            = when (project.id) {
                null -> projectRepository.insert(project)
                else -> projectRepository.save(project)
            }

    fun getProjectDtoById(id: String): ProjectDto? {
        val project = projectRepository.findOne(id)
        return when(project) {
            null -> null
            else -> projectDtoMapper.toDto(project)
        }
    }

    fun getProjectById(id: String): Project?
            = projectRepository.findOne(id)

    fun getPositionsByProject(projectId: String)
            = getProjectDtoById(projectId)?.positions

    fun getPositionById(projectId: String, positionId: String)
            = getPositionsByProject(projectId)?.firstOrNull { it.id == positionId }

    fun getProjectsByUser(userId: String): List<ProjectDto>? {
        throw NotImplementedError("method is not implemented yet")
    }

    fun getAllProjects(): List<ProjectDto>?
            = projectRepository.findAll().map {
                it -> projectDtoMapper.toDto(it)
            }

    fun removeSilently(id: String): Boolean {
        val project = projectRepository.findOne(id)
        return when (project) {
            null -> false
            else -> {
                project.baseInfo = BaseInfo(Date(), true)
                projectRepository.save(project)
                true
            }
        }
    }

    fun removeSilently(projectDto: ProjectDto): Boolean {
        projectRepository.save(projectDtoMapper.toDomain(projectDto)
                .apply { this.baseInfo = BaseInfo(Date(), true) })
        return true
    }

    fun delete(id: String) = projectRepository.delete(id)

    fun addPosition(projectId: String, positionDto: PositionDto)
            = projectRepository.addPosition(projectId, positionDtoMapper.toDomain(positionDto))

    fun setUserToPosition(projectId: String, positionId: String, userId: String): Boolean {
        val position = getPositionById(projectId, positionId)
        return if(position != null && position.employeeId == null) {
            position.employeeId = userId
            projectRepository.updatePosition(projectId, positionDtoMapper.toDomain(position))
            true
        } else {
            false
        }
    }

    fun getProjectsByIds(ids: List<String>?)
            = when (ids) {
                null -> null
                else -> projectRepository.getProjectsByIds(ids).map { it -> projectMinifiedVersionMapper.toDto(it) }
            }
}