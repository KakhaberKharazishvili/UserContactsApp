package com.example.domain.model

data class Contact(
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val birthDate: String,
    val imageUri: String,
    val category: ContactCategory
)