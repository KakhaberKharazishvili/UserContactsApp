package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.ContactCategory

@Entity(tableName = "contacts")
internal data class ContactEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val birthDate: String,
    val imageUri: String,
    val category: ContactCategory
)

