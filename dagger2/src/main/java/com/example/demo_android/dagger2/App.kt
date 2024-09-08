package com.example.demo_android.dagger2

import android.app.Application
import com.example.demo_android.dagger2.di.AppComponent
import com.example.demo_android.dagger2.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}
