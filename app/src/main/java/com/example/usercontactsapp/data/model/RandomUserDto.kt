package com.example.usercontactsapp.data.model

data class RandomUserResponse(
    val results: List<RandomUserDto>
)

data class RandomUserDto(
    val name: NameDto,
    val email: String,
    val phone: String,
    val dob: DobDto,
    val picture: PictureDto
)

data class NameDto(
    val first: String, val last: String
)

data class DobDto(
    val date: String
)

data class PictureDto(
    val large: String
)
