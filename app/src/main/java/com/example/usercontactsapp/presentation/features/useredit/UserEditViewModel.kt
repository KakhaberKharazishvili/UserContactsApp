package com.example.usercontactsapp.presentation.features.useredit

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercontactsapp.presentation.model.UserUiModel
import com.example.usercontactsapp.presentation.model.toDomain
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import androidx.core.net.toUri

class UserEditViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UserEditState())
    val state: StateFlow<UserEditState> = _state.asStateFlow()

    private val _onSaveSuccess = MutableSharedFlow<Unit>()
    val onSaveSuccess = _onSaveSuccess.asSharedFlow()

    private var currentUserId: Int = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUser().collect { user ->
                user?.let {
                    currentUserId = it.id
                    _state.update { current ->
                        current.copy(
                            firstName = it.firstName,
                            lastName = it.lastName,
                            phone = it.phone,
                            email = it.email,
                            birthDate = it.birthDate,
                            imageUri = it.imageUri.toUri()
                        )
                    }
                }
            }
        }
    }

    fun onFirstNameChange(value: String) {
        _state.update { it.copy(firstName = value) }
    }

    fun onLastNameChange(value: String) {
        _state.update { it.copy(lastName = value) }
    }

    fun onPhoneChange(value: String) {
        _state.update { it.copy(phone = value) }
    }

    fun onEmailChange(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun onBirthDateChange(value: String) {
        _state.update { it.copy(birthDate = value) }
    }

    fun onImageChange(uri: Uri) {
        _state.update { it.copy(imageUri = uri) }
    }

    fun onSaveClicked() {
        val current = _state.value
        val user = UserUiModel(
            id = currentUserId,
            firstName = current.firstName,
            lastName = current.lastName,
            phone = current.phone,
            email = current.email,
            birthDate = current.birthDate,
            imageUri = current.imageUri?.toString() ?: ""
        )

        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user.toDomain())
            _onSaveSuccess.emit(Unit)
        }
    }
}
