package com.scivantage.service

import com.coworkio.dto.ProjectDto
import com.coworkio.entity.domain.Project
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