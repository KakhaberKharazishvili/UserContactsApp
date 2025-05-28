package com.example.usercontactsapp.data.mapper

import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.data.model.ContactUiModel
import com.example.usercontactsapp.data.model.RandomUserDto

fun RandomUserDto.toUiModel(category: ContactCategory): ContactUiModel = ContactUiModel(
    0, name.first, name.last, phone, email, dob.date, picture.large, category
)
