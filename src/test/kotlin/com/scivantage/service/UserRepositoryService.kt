package com.scivantage.service

import com.coworkio.entity.domain.BaseInfo
import com.coworkio.entity.domain.User
import com.coworkio.entity.domain.enum.NotificationPreferences
import com.coworkio.entity.domain.enum.Role
import com.coworkio.service.domain.UserService
import com.scivantage.AbstractTestCase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class UserRepositoryTest : AbstractTestCase() {

    private lateinit var user: User

    @Autowired
    private lateinit var userService: UserService

    @Before
    fun populateTestData() {
        user = User(
                id = null,
                baseInfo = BaseInfo(Date(), true),
                firstName = "test_user",
                middleName = null,
                lastName = "test_user",
                role = Role.STUDENT,
                photoUrl = null,
                email = "test@user.com",
                password = "qwerty123",
                accountConfirmed = false,
                notificationPreferences = NotificationPreferences.ALL,
                github = null,
                accounts = null,
                phoneNumber = null,
                projects = null,
                skills = null,
                university = null
        )
        user = userService.saveOrUpdate(user)
        Assert.assertNotNull(user.id)
    }

    @Test
    fun updateUser(){
        user.firstName = "name"
        val newUser = userService.saveOrUpdate(user)
        Assert.assertEquals(newUser.firstName, user.firstName)
    }

    @Test
    fun findAllUsers(){
        val userList = userService.findAll()
        Assert.assertTrue(userList.contains(user))
    }

    @Test
    fun findUserById(){
        if(user.id != null) {
            val foundUser = userService.findById(user.id!!)
            Assert.assertEquals(foundUser, user)
        }
    }

    @Test
    fun findUserByEmail(){
        val foundUser = userService.findByEmail(user.email)
        Assert.assertEquals(foundUser, user)
    }

    @Test
    fun getUsersCount(){
        val usersCount = userService.count()
        Assert.assertNotEquals(usersCount, 0)
    }

    @After
    fun removeTestData() {
        userService.delete(user)
    }
}