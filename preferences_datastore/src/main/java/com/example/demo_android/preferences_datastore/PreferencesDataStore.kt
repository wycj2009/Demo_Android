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
import kotlin.reflect.KClass

abstract class PreferencesDataStore {
    abstract val dataStore: DataStore<Preferences>
}

class Pref<T : Any>(
    private val clazz: KClass<T>,
    private val dataStore: DataStore<Preferences>,
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
        return when (clazz) {
            Boolean::class -> booleanPreferencesKey(key)
            Int::class -> intPreferencesKey(key)
            Long::class -> longPreferencesKey(key)
            Float::class -> floatPreferencesKey(key)
            Double::class -> doublePreferencesKey(key)
            String::class -> stringPreferencesKey(key)
            Set::class -> stringSetPreferencesKey(key)
            else -> throw IllegalArgumentException()
        } as Preferences.Key<T>
    }

    companion object {
        inline operator fun <reified T : Any> invoke(
            dataStore: DataStore<Preferences>,
            key: String,
            defaultValue: T,
        ): Pref<T> = Pref(T::class, dataStore, key, defaultValue)
    }
}

inline fun <reified T : Any> PreferencesDataStore.pref(key: String, defaultValue: T): Pref<T> = Pref(T::class, this.dataStore, key, defaultValue)
