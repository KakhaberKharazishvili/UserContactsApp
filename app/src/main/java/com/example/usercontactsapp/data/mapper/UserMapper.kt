package com.example.usercontactsapp.data.mapper

import com.example.usercontactsapp.data.local.UserEntity
import com.example.usercontactsapp.presentation.model.UserUiModel

fun UserEntity.toUiModel(): UserUiModel = UserUiModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    birthDate = birthDate,
    imageUri = imageUri
)

fun UserUiModel.toEntity(): UserEntity = UserEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    birthDate = birthDate,
    imageUri = imageUri
)
