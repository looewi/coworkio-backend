package com.coworkio.repository

import com.coworkio.entity.domain.Project
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository: MongoRepository<Project, String>, CustomProjectRepository {


}