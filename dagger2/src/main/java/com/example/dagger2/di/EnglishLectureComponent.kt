package com.example.dagger2.di

import com.example.dagger2.EnglishGrammarLectureActivity
import com.example.dagger2.EnglishWordLectureActivity
import dagger.Subcomponent

@Subcomponent
interface EnglishLectureComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): EnglishLectureComponent
    }

    fun inject(activity: EnglishWordLectureActivity)
    fun inject(activity: EnglishGrammarLectureActivity)
}
