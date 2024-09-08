package com.example.dagger2.di

import com.example.dagger2.MathArithmeticLectureActivity
import dagger.Subcomponent

@Subcomponent
interface MathLectureComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MathLectureComponent
    }

    fun inject(activity: MathArithmeticLectureActivity)
}
