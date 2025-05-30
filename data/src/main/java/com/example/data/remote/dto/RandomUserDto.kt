package com.example.data.remote.dto

internal data class RandomUserDto(
    val name: NameDto,
    val email: String,
    val phone: String,
    val dob: DobDto,
    val picture: PictureDto
)

internal data class RandomUserResponse(
    val results: List<RandomUserDto>
)

internal data class NameDto(
    val first: String,
    val last: String
)

internal data class DobDto(
    val date: String
)

internal data class PictureDto(
    val large: String
)
