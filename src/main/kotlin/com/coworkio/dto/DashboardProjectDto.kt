package com.coworkio.dto

import com.coworkio.entity.domain.Project

data class DashboardProjectDto(val project: Project, val isCurrent: Boolean)