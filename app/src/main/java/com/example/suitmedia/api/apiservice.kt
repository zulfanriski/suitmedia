package com.example.suitmedia.api

import com.example.suitmedia.data.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface apiservice {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<UserResponse>
}