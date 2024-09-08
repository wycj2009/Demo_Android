package com.example.dagger2.di

import dagger.Module

@Module(subcomponents = [MathLectureComponent::class, EnglishLectureComponent::class])
class AppSubComponents
