package com.example.demo_android.dagger2

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class EnglishWordLectureViewModel @Inject constructor(
    private val appDataManager: AppDataManager,
    private val englishLectureRepository: EnglishLectureRepository
) {

    fun getInjectedFieldsInfo(): String {
        return "${appDataManager::class.simpleName}(${appDataManager.hashCode()})\n${englishLectureRepository::class.simpleName}(${englishLectureRepository.hashCode()})"
    }

    fun getLastAppLunchTime(): String? {
        return appDataManager.lastAppLunchTime
    }

    fun getLastEnglishWordLectureEntryTime(): String? {
        return englishLectureRepository.lastEnglishWordLectureEntryTime
    }

    fun setLastEnglishWordLectureEntryTime() {
        englishLectureRepository.lastEnglishWordLectureEntryTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    }

    fun getWords(): String {
        return englishLectureRepository.words
    }
}
