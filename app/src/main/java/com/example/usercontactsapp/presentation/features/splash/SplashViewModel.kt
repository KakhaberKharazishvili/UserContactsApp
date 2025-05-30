package com.example.usercontactsapp.presentation.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.UserRepository
import com.example.usercontactsapp.presentation.features.navigation.AppDestination
import com.example.usercontactsapp.presentation.features.navigation.UserForm
import com.example.usercontactsapp.presentation.features.navigation.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SplashViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _startDestination = MutableSharedFlow<AppDestination>(replay = 1)
    val startDestination: SharedFlow<AppDestination> = _startDestination.asSharedFlow()

    fun checkStartDestination() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUser().collect { user ->
                val destination = if (user != null) UserInfo else UserForm
                _startDestination.emit(destination)
            }
        }
    }
}
