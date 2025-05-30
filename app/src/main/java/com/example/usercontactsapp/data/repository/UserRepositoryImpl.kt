package com.example.usercontactsapp.data.repository

import com.example.usercontactsapp.data.local.UserDao
import kotlinx.coroutines.flow.Flow
import com.example.usercontactsapp.presentation.model.UserUiModel
import com.example.usercontactsapp.data.mapper.toEntity
import com.example.usercontactsapp.data.mapper.toUiModel
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override fun getUser(): Flow<UserUiModel?> {
        return userDao.getUser().map { it?.toUiModel() }
    }

    override suspend fun insertUser(user: UserUiModel) {
        userDao.insertUser(user.toEntity())
    }

    override suspend fun updateUser(user: UserUiModel) {
        userDao.updateUser(user.toEntity())
    }
}

