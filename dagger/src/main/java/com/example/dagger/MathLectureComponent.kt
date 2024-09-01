package com.example.dagger

import dagger.Subcomponent

@Subcomponent
interface MathLectureComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MathLectureComponent
    }

    fun inject(activity: MathArithmeticLectureActivity)
}
