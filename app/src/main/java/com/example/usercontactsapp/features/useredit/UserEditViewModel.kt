package com.example.usercontactsapp.features.useredit

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercontactsapp.data.model.UserUiModel
import com.example.usercontactsapp.data.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserEditViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _firstName = MutableStateFlow("")
    val firstName: StateFlow<String> = _firstName

    private val _lastName = MutableStateFlow("")
    val lastName: StateFlow<String> = _lastName

    private val _phone = MutableStateFlow("")
    val phone: StateFlow<String> = _phone

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _birthDate = MutableStateFlow("")
    val birthDate: StateFlow<String> = _birthDate

    private val _imageUri = MutableStateFlow<Uri?>(null)
    val imageUri: StateFlow<Uri?> = _imageUri

    private val _onSaveSuccess = MutableSharedFlow<Unit>()
    val onSaveSuccess: SharedFlow<Unit> = _onSaveSuccess

    private var currentUserId: Int = 0

    init {
        viewModelScope.launch {
            repository.getUser().collect { user ->
                user?.let {
                    currentUserId = it.id
                    _firstName.value = it.firstName
                    _lastName.value = it.lastName
                    _phone.value = it.phone
                    _email.value = it.email
                    _birthDate.value = it.birthDate
                    _imageUri.value = Uri.parse(it.imageUri)
                }
            }
        }
    }

    fun onFirstNameChange(value: String) {
        _firstName.value = value
    }

    fun onLastNameChange(value: String) {
        _lastName.value = value
    }

    fun onPhoneChange(value: String) {
        _phone.value = value
    }

    fun onEmailChange(value: String) {
        _email.value = value
    }

    fun onBirthDateChange(value: String) {
        _birthDate.value = value
    }

    fun onImageChange(uri: Uri) {
        _imageUri.value = uri
    }

    fun onSaveClicked() {
        viewModelScope.launch {
            val user = UserUiModel(
                id = currentUserId,
                firstName = _firstName.value,
                lastName = _lastName.value,
                phone = _phone.value,
                email = _email.value,
                birthDate = _birthDate.value,
                imageUri = _imageUri.value?.toString() ?: ""
            )
            repository.updateUser(user)
            _onSaveSuccess.emit(Unit)
        }
    }
}
