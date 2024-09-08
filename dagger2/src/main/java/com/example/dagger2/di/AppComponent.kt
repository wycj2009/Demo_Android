package com.example.dagger2.di

import android.content.Context
import com.example.dagger2.AppDataManager
import com.example.dagger2.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubComponents::class, StorageModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun mathLectureComponent(): MathLectureComponent.Factory
    fun englishLectureComponent(): EnglishLectureComponent.Factory

    fun appDataManager(): AppDataManager

    fun inject(activity: MainActivity)
}
