package com.example.demo_android.dagger2

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EnglishLectureRepository @Inject constructor(
    private val storage: Storage
) {
    var lastEnglishWordLectureEntryTime: String?
        get() = storage.getString("last_english_word_lecture_entry_time")
        set(value) {
            storage.setString("last_english_word_lecture_entry_time", value)
        }
    var lastEnglishGrammarLectureEntryTime: String?
        get() = storage.getString("last_english_grammar_lecture_entry_time")
        set(value) {
            storage.setString("last_english_grammar_lecture_entry_time", value)
        }
    val words: String = """
        dolphin - 돌고래
        elephant - 코끼리
        eagle - 독수리
    """.trimIndent()
    val grammar: String = """
        1형식: 주어 + 동사
        2형식: 주어 + 동사 + 보어(주격 보어)
        3형식: 주어 + 동사 + 목적어
        4형식: 주어 + 동사 + 간접 목적어 + 직접 목적어
        5형식: 주어 + 동사 + 목적어 + 보어(목적격 보어)
    """.trimIndent()
}
