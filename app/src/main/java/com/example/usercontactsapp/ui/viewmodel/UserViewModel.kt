package com.example.usercontactsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercontactsapp.data.model.User
import com.example.usercontactsapp.data.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.onEach
import androidx.compose.runtime.mutableStateOf
import android.util.Log
import kotlinx.coroutines.flow.onStart

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _isLoaded = mutableStateOf(false)
    val isLoaded: Boolean get() = _isLoaded.value

    val user: StateFlow<User?> = repository.getUser().onStart { _isLoaded.value = false }.onEach {
        Log.d("UserViewModel", "User loaded: $it")
        _isLoaded.value = true
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    suspend fun insertUser(user: User) {
        repository.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        repository.updateUser(user)
    }
}


