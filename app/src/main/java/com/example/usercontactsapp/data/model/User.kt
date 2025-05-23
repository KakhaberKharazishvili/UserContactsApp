package com.example.usercontactsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val birthDate: String,
    val imageUri: String
)
