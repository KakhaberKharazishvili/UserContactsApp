package com.example.usercontactsapp.presentation.features.userform

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.UserRepository
import com.example.usercontactsapp.presentation.model.UserUiModel
import com.example.usercontactsapp.presentation.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserFormViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UserFormState())
    val state: StateFlow<UserFormState> = _state.asStateFlow()

    private val _onSaveSuccess = MutableSharedFlow<Unit>()
    val onSaveSuccess = _onSaveSuccess.asSharedFlow()

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
        if (current.firstName.isNotBlank() && current.lastName.isNotBlank()) {
            val user = UserUiModel(
                id = 0,
                firstName = current.firstName,
                lastName = current.lastName,
                phone = current.phone,
                email = current.email,
                birthDate = current.birthDate,
                imageUri = current.imageUri?.toString() ?: ""
            )

            viewModelScope.launch(Dispatchers.IO) {
                repository.insertUser(user.toDomain())
                _onSaveSuccess.emit(Unit)
            }
        }
    }
}
