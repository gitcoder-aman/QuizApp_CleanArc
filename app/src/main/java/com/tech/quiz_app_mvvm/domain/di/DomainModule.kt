package com.tech.quiz_app_mvvm.domain.di

import com.tech.quiz_app_mvvm.data.remote.dto.QuizApi
import com.tech.quiz_app_mvvm.data.repository.QuizRepositoryImpl
import com.tech.quiz_app_mvvm.domain.repository.QuizRepository
import com.tech.quiz_app_mvvm.domain.usecases.GetQuizzesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideGetUseCases(quizRepository: QuizRepository) : GetQuizzesUseCases{
        return GetQuizzesUseCases(quizRepository)
    }
}