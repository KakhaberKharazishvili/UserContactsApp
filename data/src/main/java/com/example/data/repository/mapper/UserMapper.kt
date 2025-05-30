package com.example.data.repository.mapper

import com.example.data.local.entity.UserEntity
import com.example.domain.model.User

internal fun UserEntity.toDomain(): User = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    birthDate = birthDate,
    imageUri = imageUri
)

internal fun User.toEntity(): UserEntity = UserEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    birthDate = birthDate,
    imageUri = imageUri
)
