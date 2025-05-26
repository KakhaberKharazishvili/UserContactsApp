package com.example.usercontactsapp.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercontactsapp.data.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.onEach
import androidx.compose.runtime.mutableStateOf
import com.example.usercontactsapp.data.model.UserUiModel
import kotlinx.coroutines.flow.onStart

class SplashViewModel(
    repository: UserRepository
) : ViewModel() {

    private val _isLoaded = mutableStateOf(false)
    val isLoaded: Boolean get() = _isLoaded.value

    val user: StateFlow<UserUiModel?> =
        repository.getUser().onStart { _isLoaded.value = false }.onEach { _isLoaded.value = true }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
}
