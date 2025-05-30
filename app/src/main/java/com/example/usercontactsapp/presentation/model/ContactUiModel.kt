package com.example.usercontactsapp.presentation.model

import com.example.usercontactsapp.data.local.ContactCategory

data class ContactUiModel(
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val birthDate: String,
    val imageUri: String,
    val category: ContactCategory
)
