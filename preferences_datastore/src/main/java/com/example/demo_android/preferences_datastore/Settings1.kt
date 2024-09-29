package com.example.demo_android.preferences_datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile

// 의존성 주입 후 사용
class Settings1 private constructor(applicationContext: Context) : PreferencesDataStore() {
    override val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create {
        applicationContext.preferencesDataStoreFile(name = "settings_1")
    }

    val boolean: Pref<Boolean> = Pref(key = "boolean", defaultValue = false)
    val int: Pref<Int> = Pref(key = "int", defaultValue = 0)
    val long: Pref<Long> = Pref(key = "long", defaultValue = 0L)
    val float: Pref<Float> = Pref(key = "float", defaultValue = 0f)
    val double: Pref<Double> = Pref(key = "double", defaultValue = 0.0)
    val string: Pref<String> = Pref(key = "string", defaultValue = "")
    val stringSet: Pref<Set<String>> = Pref(key = "stringSet", defaultValue = setOf())

    companion object {
        private var instance: Settings1? = null
        fun getInstance(applicationContext: Context): Settings1 {
            return instance ?: synchronized(this) {
                instance ?: Settings1(applicationContext).also {
                    instance = it
                }
            }
        }
    }
}
