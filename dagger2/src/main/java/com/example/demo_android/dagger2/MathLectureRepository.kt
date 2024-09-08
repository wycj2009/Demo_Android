package com.example.demo_android.dagger2

import javax.inject.Inject

class MathLectureRepository @Inject constructor(
    private val storage: Storage
) {
    var lastMathArithmeticLectureEntryTime: String?
        get() = storage.getString("last_math_arithmetic_lecture_entry_time")
        set(value) {
            storage.setString("last_math_arithmetic_lecture_entry_time", value)
        }
    val arithmetic: String = """
        4 + 2 = 6
        4 - 2 = 2
        4 * 2 = 8
        4 / 2 = 2
    """.trimIndent()
}
