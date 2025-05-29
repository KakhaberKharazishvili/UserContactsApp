package com.example.usercontactsapp.presentation.features.userform

import android.net.Uri

data class UserFormState(
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val email: String = "",
    val birthDate: String = "",
    val imageUri: Uri? = null
)
