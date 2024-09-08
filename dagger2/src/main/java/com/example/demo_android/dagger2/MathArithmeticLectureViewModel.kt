package com.example.demo_android.dagger2

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class MathArithmeticLectureViewModel @Inject constructor(
    private val appDataManager: AppDataManager,
    private val mathLectureRepository: MathLectureRepository
) {

    fun getInjectedFieldsInfo(): String {
        return "${appDataManager::class.simpleName}(${appDataManager.hashCode()})\n${mathLectureRepository::class.simpleName}(${mathLectureRepository.hashCode()})"
    }

    fun getLastAppLunchTime(): String? {
        return appDataManager.lastAppLunchTime
    }

    fun getLastMathArithmeticLectureEntryTime(): String? {
        return mathLectureRepository.lastMathArithmeticLectureEntryTime
    }

    fun setLastMathArithmeticLectureEntryTime() {
        mathLectureRepository.lastMathArithmeticLectureEntryTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    }

    fun getArithmetic(): String {
        return mathLectureRepository.arithmetic
    }
}
