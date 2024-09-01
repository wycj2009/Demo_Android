package com.example.dagger.di

import dagger.Module

@Module(subcomponents = [MathLectureComponent::class, EnglishLectureComponent::class])
class AppSubComponents
