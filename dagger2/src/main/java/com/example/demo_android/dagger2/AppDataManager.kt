package com.example.demo_android.dagger2

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val storage: Storage
) {
    var lastAppLunchTime: String?
        get() = storage.getString("last_app_lunch_time")
        set(value) {
            storage.setString("last_app_lunch_time", value)
        }
}
