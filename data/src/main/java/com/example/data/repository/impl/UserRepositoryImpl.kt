package com.example.data.repository.impl

import com.example.data.local.dao.UserDao
import com.example.data.repository.mapper.toDomain
import com.example.data.repository.mapper.toEntity
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override fun getUser(): Flow<User?> {
        return userDao.getUser().map { it?.toDomain() }
    }

    override suspend fun insertUser(user: User) {
        userDao.insertUser(user.toEntity())
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(user.toEntity())
    }
}
