package com.example.usercontactsapp.data.mapper

import com.example.usercontactsapp.data.local.ContactEntity
import com.example.usercontactsapp.data.model.ContactUiModel

fun ContactEntity.toUiModel(): ContactUiModel = ContactUiModel(
    id, firstName, lastName, phone, email, birthDate, imageUri, category
)

fun ContactUiModel.toEntity(): ContactEntity = ContactEntity(
    id, firstName, lastName, phone, email, birthDate, imageUri, category
)
