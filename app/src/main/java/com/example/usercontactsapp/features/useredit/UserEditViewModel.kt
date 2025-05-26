package com.example.usercontactsapp.features.useredit

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercontactsapp.data.repository.UserRepository
import androidx.compose.runtime.mutableStateOf
import com.example.usercontactsapp.data.model.UserUiModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class UserEditViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var firstName by mutableStateOf("")
        private set

    var lastName by mutableStateOf("")
        private set

    var phone by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var birthDate by mutableStateOf("")
        private set

    var imageUri by mutableStateOf<Uri?>(null)
        private set

    private var currentUserId: Int = 0

    val isReady: Boolean
        get() = firstName.isNotBlank() && lastName.isNotBlank()

    init {
        viewModelScope.launch {
            repository.getUser().collect { user ->
                user?.let {
                    currentUserId = it.id
                    firstName = it.firstName
                    lastName = it.lastName
                    phone = it.phone
                    email = it.email
                    birthDate = it.birthDate
                    imageUri = Uri.parse(it.imageUri)
                }
            }
        }
    }

    fun onFirstNameChange(value: String) {
        firstName = value
    }

    fun onLastNameChange(value: String) {
        lastName = value
    }

    fun onPhoneChange(value: String) {
        phone = value
    }

    fun onEmailChange(value: String) {
        email = value
    }

    fun onBirthDateChange(value: String) {
        birthDate = value
    }

    fun onImageChange(uri: Uri) {
        imageUri = uri
    }

    suspend fun save() {
        val user = UserUiModel(
            id = currentUserId,
            firstName = firstName,
            lastName = lastName,
            phone = phone,
            email = email,
            birthDate = birthDate,
            imageUri = imageUri?.toString() ?: ""
        )
        repository.updateUser(user)
    }
}

