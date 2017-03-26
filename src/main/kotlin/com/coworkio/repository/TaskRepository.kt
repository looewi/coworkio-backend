package com.coworkio.repository

import com.coworkio.entity.domain.Task
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository: MongoRepository<Task, String>