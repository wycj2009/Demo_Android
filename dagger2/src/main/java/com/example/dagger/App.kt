package com.example.dagger

import android.app.Application
import com.example.dagger.di.AppComponent
import com.example.dagger.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}
