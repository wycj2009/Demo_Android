package com.example.dagger.di

import com.example.dagger.MathArithmeticLectureActivity
import dagger.Subcomponent

@Subcomponent
interface MathLectureComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MathLectureComponent
    }

    fun inject(activity: MathArithmeticLectureActivity)
}
