package com.example.usercontactsapp.presentation.features.userinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.UserRepository
import com.example.usercontactsapp.presentation.model.UserUiModel
import com.example.usercontactsapp.presentation.model.toUiModel
import kotlinx.coroutines.flow.*

class UserInfoViewModel(
    repository: UserRepository
) : ViewModel() {

    val user: StateFlow<UserUiModel?> =
        repository.getUser()
            .map { it?.toUiModel() }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
}
