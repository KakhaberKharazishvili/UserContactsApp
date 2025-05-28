package com.example.usercontactsapp.data.api

import com.example.usercontactsapp.data.model.RandomUserResponse
import retrofit2.http.GET

interface RandomUserApiService {
    @GET("api/?results=1")
    suspend fun getRandomUser(): RandomUserResponse
}
