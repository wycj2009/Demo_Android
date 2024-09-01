package com.example.dagger.di

import com.example.dagger.SharedPreferencesStorage
import com.example.dagger.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    @Binds
    abstract fun bindStorage(sharedPreferencesStorage: SharedPreferencesStorage): Storage
}
