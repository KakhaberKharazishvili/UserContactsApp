package com.example.usercontactsapp.features.userform

import android.net.Uri

data class UserFormState(
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val email: String = "",
    val birthDate: String = "",
    val imageUri: Uri? = null
)
