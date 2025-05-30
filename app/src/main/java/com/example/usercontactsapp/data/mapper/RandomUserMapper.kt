package com.example.usercontactsapp.data.mapper

import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.presentation.model.ContactUiModel
import com.example.usercontactsapp.data.model.RandomUserDto

fun RandomUserDto.toUiModel(category: ContactCategory): ContactUiModel = ContactUiModel(
    id = 0,
    firstName = name.first,
    lastName = name.last,
    phone = phone,
    email = email,
    birthDate = dob.date,
    imageUri = picture.large,
    category = category
)
