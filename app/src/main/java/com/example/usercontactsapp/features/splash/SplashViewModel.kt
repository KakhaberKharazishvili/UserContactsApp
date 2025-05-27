package com.example.usercontactsapp.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercontactsapp.data.repository.UserRepository
import com.example.usercontactsapp.features.navigation.AppDestination
import com.example.usercontactsapp.features.navigation.UserForm
import com.example.usercontactsapp.features.navigation.UserInfo
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
