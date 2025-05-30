package com.example.usercontactsapp.presentation.model

import com.example.domain.model.Contact
import com.example.domain.model.ContactCategory
import com.example.domain.model.User

fun Contact.toUiModel(): ContactUiModel = ContactUiModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    birthDate = birthDate,
    imageUri = imageUri,
    category = category
)

fun ContactUiModel.toDomain(): Contact = Contact(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    birthDate = birthDate,
    imageUri = imageUri,
    category = category
)

fun User.toContactUiModel(category: ContactCategory): ContactUiModel = ContactUiModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    birthDate = birthDate,
    imageUri = imageUri,
    category = category
)

fun User.toUiModel(): UserUiModel = UserUiModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    birthDate = birthDate,
    imageUri = imageUri
)

fun UserUiModel.toDomain(): User = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    birthDate = birthDate,
    imageUri = imageUri
)
