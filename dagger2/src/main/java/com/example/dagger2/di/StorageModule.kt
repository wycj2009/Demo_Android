package com.example.dagger2.di

import com.example.dagger2.SharedPreferencesStorage
import com.example.dagger2.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    @Binds
    abstract fun bindStorage(sharedPreferencesStorage: SharedPreferencesStorage): Storage
}
