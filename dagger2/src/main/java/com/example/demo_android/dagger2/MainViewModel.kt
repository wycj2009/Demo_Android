package com.example.demo_android.dagger2

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val appDataManager: AppDataManager
) {

    fun getInjectedFieldsInfo(): String {
        return "${appDataManager::class.simpleName}(${appDataManager.hashCode()})"
    }

    fun getLastAppLunchTime(): String? {
        return appDataManager.lastAppLunchTime
    }

    fun setLastAppLunchTime() {
        appDataManager.lastAppLunchTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    }
}
