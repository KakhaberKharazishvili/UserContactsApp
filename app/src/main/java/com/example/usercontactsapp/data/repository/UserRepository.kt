package com.example.usercontactsapp.data.repository

import com.example.usercontactsapp.data.local.UserDao
import com.example.usercontactsapp.data.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    fun getUser(): Flow<User?> {
        return userDao.getUser()
    }

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}
