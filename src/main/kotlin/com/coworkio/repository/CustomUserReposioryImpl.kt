package com.coworkio.repository

import com.coworkio.entity.domain.User
import com.coworkio.entity.domain.UserSkill
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository
import java.util.*

fun MongoTemplate.simpleUpdateMethod(id: String, key: String, value: Any?, entityClass: Class<*>) = {
    this.updateFirst(
            Query.query(Criteria.where("_id").`is`(id)),
            Update.update(key, value),
            entityClass
    )
}

@Repository
open class CustomUserReposioryImpl(@Autowired val mongoTemplate: MongoTemplate): CustomUserRepository {

    override fun changePassword(userId: String, newPwd: String) {
        mongoTemplate.simpleUpdateMethod(userId, "password", newPwd as Any, User::class.java)
    }

    override fun changeProfilePicture(userId: String, url: String) {
        mongoTemplate.simpleUpdateMethod(userId, "photoUrl", url as Any, User::class.java)
    }

    override fun deleteProfilePicture(userId: String) {
        mongoTemplate.simpleUpdateMethod(userId, "photoUrl", null, User::class.java)
    }

    override fun approveSkill(userId: String, skillId: String, approvedBy: String) {
        val user = mongoTemplate.findOne(Query.query(Criteria.where("_id").`is`(userId)), User::class.java)
        user.skills?.filter {
            it.skillId == skillId
        }?.forEach {
            when(it.approvers.contains(approvedBy)) {
                true -> it.approvers.remove(approvedBy)
                false -> it.approvers.add(approvedBy)
            }
        }
        mongoTemplate.save(user)
    }

    override fun addUserSkill(userId: String, userSkill: UserSkill) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").`is`(userId)),
                Update().addToSet("skills", userSkill),
                User::class.java)
    }

    override fun updateUserSkillLevel(userId: String, skillId: String, newLevel: String) {
        val user = mongoTemplate.findOne(Query.query(Criteria.where("_id").`is`(userId)), User::class.java)
        val skills = user.skills?.filter { it.skillId != skillId }?.toMutableList()

        val skillToUpdate = user.skills?.filter { it.skillId == skillId }?.firstOrNull()
        if(skillToUpdate != null) {
            skills?.add(skillToUpdate.copy(skillId = newLevel))
        }
        mongoTemplate.save(user.copy(skills = skills))
    }

    override fun deleteUserSkill(userId: String, skillId: String) {
        val user = mongoTemplate.findOne(Query.query(Criteria.where("_id").`is`(userId)), User::class.java)
        val skills = user.skills?.filter { it.skillId != skillId }
        mongoTemplate.save(user.copy(skills = skills))
    }

    override fun leaveProject(userId: String, projectId: String) {
        val user = mongoTemplate.findOne(Query.query(Criteria.where("_id").`is`(userId)), User::class.java)
        val projects = user.projects?.filter { it.projectId != projectId }?.toMutableList()

        val projectToUpdate = user.projects?.filter { it.projectId == projectId }?.firstOrNull()
        if(projectToUpdate != null) {
            projects?.add(projectToUpdate.copy(isCurrent = false, endDate = Date()))
        }
        mongoTemplate.save(user.copy(projects = projects))
    }

}
