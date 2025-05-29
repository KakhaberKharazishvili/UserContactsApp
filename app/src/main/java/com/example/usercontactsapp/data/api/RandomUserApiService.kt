package com.example.usercontactsapp.data.api

import com.example.usercontactsapp.data.model.RandomUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApiService {

    @GET("api/?results=1")
    suspend fun getRandomUser(): RandomUserResponse

    @GET("api/")
    suspend fun getUsers(
        @Query("results") results: Int
    ): RandomUserResponse
}
