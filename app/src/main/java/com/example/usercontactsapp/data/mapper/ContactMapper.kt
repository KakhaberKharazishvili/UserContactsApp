package com.example.usercontactsapp.data.mapper

import com.example.usercontactsapp.data.local.ContactEntity
import com.example.usercontactsapp.presentation.model.ContactUiModel

fun ContactEntity.toUiModel(): ContactUiModel = ContactUiModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    birthDate = birthDate,
    imageUri = imageUri,
    category = category
)

fun ContactUiModel.toEntity(): ContactEntity = ContactEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    birthDate = birthDate,
    imageUri = imageUri,
    category = category
)