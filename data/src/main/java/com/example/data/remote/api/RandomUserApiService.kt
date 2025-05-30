package com.example.data.remote.api

import com.example.data.remote.dto.RandomUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RandomUserApiService {

    @GET("api/")
    suspend fun getUsers(
        @Query("results") results: Int
    ): RandomUserResponse
}
