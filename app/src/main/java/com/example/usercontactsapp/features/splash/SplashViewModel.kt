package com.example.usercontactsapp.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercontactsapp.data.repository.UserRepository
import com.example.usercontactsapp.features.navigation.AppDestination
import com.example.usercontactsapp.features.navigation.UserForm
import com.example.usercontactsapp.features.navigation.UserInfo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class SplashViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _startDestination = MutableStateFlow<AppDestination>(UserForm)
    val startDestination: StateFlow<AppDestination> = _startDestination

    fun checkStartDestination() {
        if (!_isLoading.value) return

        viewModelScope.launch(Dispatchers.IO) {
            repository.getUser().collect { user ->
                _startDestination.value = if (user != null) UserInfo else UserForm
                _isLoading.value = false
            }
        }
    }
}