package com.example.data.repository.mapper

import com.example.data.remote.dto.RandomUserDto
import com.example.domain.model.User

internal fun RandomUserDto.toDomain(): User = User(
    id = 0,
    firstName = name.first,
    lastName = name.last,
    phone = phone,
    email = email,
    birthDate = dob.date,
    imageUri = picture.large
)
