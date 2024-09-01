package com.example.dagger

import dagger.Module

@Module(subcomponents = [MathLectureComponent::class, EnglishLectureComponent::class])
class AppSubComponents
