package com.example.dagger

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class SharedPreferencesStorage @Inject constructor(
    context: Context
) : Storage {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_data.pref", Context.MODE_PRIVATE)

    override fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    override fun setString(key: String, value: String?) {
        sharedPreferences.edit(true) {
            putString(key, value)
        }
    }
}
