package com.example.demo_android.preferences_datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

abstract class PreferencesDataStore {
    protected abstract val dataStore: DataStore<Preferences>

    inner class Pref<T>(
        private val key: String,
        private val defaultValue: T,
    ) {
        fun getFlow(): Flow<T> = dataStore.data.map {
            it[preferencesKey()] ?: defaultValue
        }

        fun get(): T = runBlocking {
            getFlow().firstOrNull() ?: defaultValue
        }

        fun put(value: T) {
            runBlocking {
                dataStore.edit {
                    it[preferencesKey()] = value
                }
            }
        }

        @Suppress("UNCHECKED_CAST")
        private fun preferencesKey(): Preferences.Key<T> {
            return when (defaultValue) {
                is Boolean -> booleanPreferencesKey(key)
                is Int -> intPreferencesKey(key)
                is Long -> longPreferencesKey(key)
                is Float -> floatPreferencesKey(key)
                is Double -> doublePreferencesKey(key)
                is String -> stringPreferencesKey(key)
                is Set<*> -> stringSetPreferencesKey(key)
                else -> throw IllegalStateException()
            } as Preferences.Key<T>
        }
    }
}
