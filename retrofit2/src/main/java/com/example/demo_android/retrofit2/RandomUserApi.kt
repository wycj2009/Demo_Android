package com.example.demo_android.retrofit2

import retrofit2.http.GET

interface RandomUserApi {
    @GET("/api")
    suspend fun getRandomUser(): GetRandomUserResponse
}
