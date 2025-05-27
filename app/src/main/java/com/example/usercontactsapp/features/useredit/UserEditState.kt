package com.example.usercontactsapp.features.useredit

import android.net.Uri

data class UserEditState(
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val email: String = "",
    val birthDate: String = "",
    val imageUri: Uri? = null
)
