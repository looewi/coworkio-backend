package com.coworkio.service.domain

import com.coworkio.dto.PositionDto
import com.coworkio.dto.NewProjectDto
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
open class ProjectService {

    @Autowired
    private lateinit var projectDtoMapper: ProjectDtoMapper

    @Autowired
    private lateinit var projectMinifiedVersionMapper: ProjectMinifiedVersionDtoMapper

    @Autowired
    private lateinit var positionDtoMapper: PositionDtoMapper

    @Autowired
    private lateinit var projectRepository: ProjectRepository


    fun saveOrUpdate(newProjectDto: NewProjectDto): Project {
        val project = projectDtoMapper.toDomain(newProjectDto)
        return saveOrUpdate(project)
    }

    fun saveOrUpdate(project: Project)
            = if(project.id != null) {
                projectRepository.save(project)
            } else {
                projectRepository.insert(project)
            }

    fun getProjectDtoById(id: String): NewProjectDto? {
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

    fun getAllProjects(): List<NewProjectDto>?
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

    fun delete(id: String)
            = projectRepository.delete(id)

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
            = if(ids != null) {
                projectRepository.getProjectsByIds(ids).map {
                    it -> projectMinifiedVersionMapper.toDto(it)
                }
            } else {
                null
            }
}