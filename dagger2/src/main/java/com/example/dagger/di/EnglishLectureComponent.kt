package com.example.dagger.di

import com.example.dagger.EnglishGrammarLectureActivity
import com.example.dagger.EnglishWordLectureActivity
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
