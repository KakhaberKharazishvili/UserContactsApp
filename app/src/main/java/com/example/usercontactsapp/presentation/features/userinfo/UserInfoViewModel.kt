package com.example.usercontactsapp.presentation.features.userinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercontactsapp.data.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import com.example.usercontactsapp.presentation.model.UserUiModel

class UserInfoViewModel(
    repository: UserRepository
) : ViewModel() {

    val user: StateFlow<UserUiModel?> =
        repository.getUser().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
}
