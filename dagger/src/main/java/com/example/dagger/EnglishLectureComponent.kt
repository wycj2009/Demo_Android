package com.example.dagger

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
