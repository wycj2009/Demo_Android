package com.example.demo_android.dagger2.di

import com.example.demo_android.dagger2.SharedPreferencesStorage
import com.example.demo_android.dagger2.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    @Binds
    abstract fun bindStorage(sharedPreferencesStorage: SharedPreferencesStorage): Storage
}
