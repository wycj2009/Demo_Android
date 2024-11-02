package com.example.demo_android.retrofit2

data class GetRandomUserResponse(
    val results: List<User>,
    val info: Info,
) {
    data class Info(
        val seed: String,
        val results: Int,
        val page: Int,
        val version: String,
    )
}
