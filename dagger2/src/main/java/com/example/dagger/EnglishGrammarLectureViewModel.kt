package com.example.dagger

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class EnglishGrammarLectureViewModel @Inject constructor(
    private val appDataManager: AppDataManager,
    private val englishLectureRepository: EnglishLectureRepository
) {

    fun getInjectedFieldsInfo(): String {
        return "${appDataManager::class.simpleName}(${appDataManager.hashCode()})\n${englishLectureRepository::class.simpleName}(${englishLectureRepository.hashCode()})"
    }

    fun getLastAppLunchTime(): String? {
        return appDataManager.lastAppLunchTime
    }

    fun getLastEnglishGrammarLectureEntryTime(): String? {
        return englishLectureRepository.lastEnglishGrammarLectureEntryTime
    }

    fun setLastEnglishGrammarLectureEntryTime() {
        englishLectureRepository.lastEnglishGrammarLectureEntryTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    }

    fun getGrammar(): String {
        return englishLectureRepository.grammar
    }
}
