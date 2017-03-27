package com.coworkio.repository

import com.coworkio.entity.domain.UserSkill

interface CustomUserRepository {
    fun changePassword(userId:String, newPwd: String)
    fun changeProfilePicture(userId: String, url: String)
    fun deleteProfilePicture(userId: String)
    fun approveSkill(userId: String, skillId: String, approvedBy: String)
    fun addUserSkill(userId: String, userSkill: UserSkill) //TODO skill also should be added to the Skill collection
    fun updateUserSkillLevel(userId: String, skillId: String, newLevel: String)
    fun deleteUserSkill(userId: String, skillId: String)
    fun leaveProject(userId: String, projectId: String)
}