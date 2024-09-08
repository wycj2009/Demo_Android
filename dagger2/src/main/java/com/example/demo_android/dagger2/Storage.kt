package com.example.demo_android.dagger2

interface Storage {
    fun getString(key: String): String?
    fun setString(key: String, value: String?)
}
