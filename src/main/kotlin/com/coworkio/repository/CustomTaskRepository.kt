package com.coworkio.repository

import com.coworkio.entity.domain.Task
import org.springframework.stereotype.Repository

/**
 * Created by Mary on 6/14/2017.
 */


@Repository
interface CustomTaskRepository {
    fun  find(title: String?, priority: String?, assignee: String?): List<Task>?
}
