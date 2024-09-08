package com.example.demo_android.dagger2.di

import com.example.demo_android.dagger2.MathArithmeticLectureActivity
import dagger.Subcomponent

@Subcomponent
interface MathLectureComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MathLectureComponent
    }

    fun inject(activity: MathArithmeticLectureActivity)
}
