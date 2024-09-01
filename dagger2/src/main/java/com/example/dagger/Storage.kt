package com.example.dagger

interface Storage {
    fun getString(key: String): String?
    fun setString(key: String, value: String?)
}
