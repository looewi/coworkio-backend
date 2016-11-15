package com.coworkio.service.domain

import com.coworkio.dto.PositionDto
import com.coworkio.dto.ProjectDto
import com.coworkio.dto.mapper.PositionDtoMapper
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
    private lateinit var projectDtoMapper: ProjectDtoMapper

    @Autowired
    private lateinit var positionDtoMapper: PositionDtoMapper

    @Autowired
    private lateinit var projectRepository: ProjectRepository


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

    fun addPosition(projectId: String, positionDto: PositionDto)
            = projectRepository.addPosition(projectId, positionDtoMapper.toDomain(positionDto))

    fun setUserToPosition(projectId: String, positionId: String, userId: String): Boolean {
        val position = getPositionById(projectId, positionId)
        return if(position != null && position?.employeeId == null) {
            position.employeeId = userId
            projectRepository.updatePosition(projectId, positionDtoMapper.toDomain(position))
            true
        } else {
            false
        }
    }
}