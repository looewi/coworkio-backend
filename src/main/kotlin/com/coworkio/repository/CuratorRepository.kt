package com.coworkio.repository

import com.coworkio.entity.domain.Curator
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CuratorRepository: MongoRepository<Curator, String>, CustomCuratorRepository