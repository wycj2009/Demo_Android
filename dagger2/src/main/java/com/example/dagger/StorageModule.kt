package com.example.dagger

import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    @Binds
    abstract fun bindStorage(sharedPreferencesStorage: SharedPreferencesStorage): Storage
}
