package com.example.usercontactsapp.data.mapper

import com.example.usercontactsapp.data.local.UserEntity
import com.example.usercontactsapp.data.model.UserUiModel

fun UserEntity.toUiModel(): UserUiModel = UserUiModel(
    id, firstName, lastName, phone, email, birthDate, imageUri
)

fun UserUiModel.toEntity(): UserEntity = UserEntity(
    id, firstName, lastName, phone, email, birthDate, imageUri
)
