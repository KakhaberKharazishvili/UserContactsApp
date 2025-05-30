package com.example.data.repository.mapper

import com.example.data.local.entity.ContactEntity
import com.example.domain.model.Contact

internal fun ContactEntity.toDomain(): Contact = Contact(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    birthDate = birthDate,
    imageUri = imageUri,
    category = category
)

internal fun Contact.toEntity(): ContactEntity = ContactEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    birthDate = birthDate,
    imageUri = imageUri,
    category = category
)
