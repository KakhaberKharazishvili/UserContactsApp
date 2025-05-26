package com.example.usercontactsapp.features.userform

import androidx.lifecycle.ViewModel
import com.example.usercontactsapp.data.repository.UserRepository
import com.example.usercontactsapp.data.model.UserUiModel

class UserFormViewModel(
    private val repository: UserRepository
) : ViewModel() {

    suspend fun insertUser(user: UserUiModel) {
        repository.insertUser(user)
    }
}
