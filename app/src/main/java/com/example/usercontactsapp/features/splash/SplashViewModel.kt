package com.example.usercontactsapp.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercontactsapp.data.repository.UserRepository
import androidx.compose.runtime.mutableStateOf
import com.example.usercontactsapp.features.navigation.AppDestination
import com.example.usercontactsapp.features.navigation.UserForm
import com.example.usercontactsapp.features.navigation.UserInfo
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class SplashViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var isLoading by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf<AppDestination>(UserForm)
        private set

    init {
        viewModelScope.launch {
            repository.getUser().collect { user ->
                startDestination = if (user != null) UserInfo else UserForm
                isLoading = false
            }
        }
    }
}
