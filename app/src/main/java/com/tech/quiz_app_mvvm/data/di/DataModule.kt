package com.tech.quiz_app_mvvm.data.di

import com.tech.quiz_app_mvvm.data.remote.dto.QuizApi
import com.tech.quiz_app_mvvm.data.repository.QuizRepositoryImpl
import com.tech.quiz_app_mvvm.domain.repository.QuizRepository
import com.tech.quiz_app_mvvm.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideQuizApi() : QuizApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApi::class.java)
    }
    @Provides
    @Singleton
    fun provideQuizRepository(quizApi: QuizApi) : QuizRepository {
        return QuizRepositoryImpl(quizApi)
    }
}