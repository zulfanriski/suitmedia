package com.example.suitmedia.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object apiconfig {
    private const val BASE_URL = "https://reqres.in/api/"

    val apiService: apiservice by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(apiservice::class.java)
    }
}