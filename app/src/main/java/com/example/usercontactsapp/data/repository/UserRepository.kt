package com.example.usercontactsapp.data.repository

import com.example.usercontactsapp.presentation.model.UserUiModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<UserUiModel?>
    suspend fun insertUser(user: UserUiModel)
    suspend fun updateUser(user: UserUiModel)
}