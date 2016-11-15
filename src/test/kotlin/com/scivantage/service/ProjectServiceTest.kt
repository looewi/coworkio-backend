package com.scivantage.service

import com.coworkio.dto.PositionDto
import com.coworkio.dto.ProjectDto
import com.coworkio.entity.domain.Project
import com.coworkio.entity.domain.enum.PositionType
import com.coworkio.service.domain.ProjectService
import com.scivantage.AbstractTestCase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class ProjectServiceTest: AbstractTestCase() {

    private lateinit var project: Project

    @Autowired
    private lateinit var projectService: ProjectService

    @Before
    fun populateData() {
        val projectDto = ProjectDto(
                id = null,
                title = "test project",
                description = "new project created by coworkio team",
                startDate = Date(),
                endDate = null,
                positions = null,
                githubLink = "lnk"
        )
        project = projectService.saveOrUpdate(projectDto)
        Assert.assertNotNull(project.id)
    }

    @Test
    fun updateProject() {
        val newDescription = "updated description"
        project.description = newDescription
        project = projectService.saveOrUpdate(project)
        Assert.assertEquals(newDescription, project.description)
    }

    @Test
    fun addPosition() {
        val position = PositionDto(
                id = Date().time.toString(),
                title = "new position",
                description = "description",
                type = PositionType.FRONTEND,
                employeeId = null
        )
        projectService.addPosition(project.id!!, position)
        val project1 = projectService.getProjectDtoById(project.id!!)
        Assert.assertEquals(project1?.positions?.size, 1)

        val position2 = PositionDto(
                id = Date().time.toString(),
                title = "new position2",
                description = "description2",
                type = PositionType.FRONTEND,
                employeeId = null
        )

        projectService.addPosition(project.id!!, position2)
        val project2 = projectService.getProjectDtoById(project.id!!)
        Assert.assertEquals(project2?.positions?.size, 2)
    }

    @Test
    fun getPositionsOfProject() {
        val position = PositionDto(
                id = Date().time.toString(),
                title = "new position",
                description = "description",
                type = PositionType.FRONTEND,
                employeeId = null
        )
        projectService.addPosition(project.id!!, position)

        val position2 = PositionDto(
                id = Date().time.toString(),
                title = "new position2",
                description = "description2",
                type = PositionType.FRONTEND,
                employeeId = null
        )

        projectService.addPosition(project.id!!, position2)

        val allPositions = projectService.getPositionsByProject(project.id!!)
        Assert.assertEquals(allPositions?.size, 2)

        val positionById = projectService.getPositionById(project.id!!, position.id)
        Assert.assertEquals(positionById, position)
    }

    @Test
    fun getProjectById() {
        val foundProject = projectService.getProjectById(project.id!!)
        Assert.assertEquals(project, foundProject)
    }

    @Test
    fun removeUnexistingProject() {
        val result = projectService.removeSilently("not existing id")
        Assert.assertFalse(result)
    }

    @Test
    fun removeProjectSilentlyById() {
        val result = projectService.removeSilently(project.id!!)
        Assert.assertTrue(result)
        val updatedProject = projectService.getProjectById(project.id!!)
        Assert.assertEquals(updatedProject?.baseInfo?.isDeleted, true)
    }

    @After
    fun doCleanup() {
        projectService.delete(project.id!!)
    }
}