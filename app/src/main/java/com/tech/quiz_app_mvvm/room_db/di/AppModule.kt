package com.tech.quiz_app_mvvm.room_db.di

import android.app.Application
import androidx.room.Room
import com.tech.quiz_app_mvvm.room_db.dao.QuizDao
import com.tech.quiz_app_mvvm.room_db.database.QuizDatabase
import com.tech.quiz_app_mvvm.room_db.utils.Constants.QUIZ_DATABASE_NAME
import com.tech.quiz_app_mvvm.room_db.utils.QuizTypeConvertor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideQuizDatabase(
        application: Application
    ): QuizDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = QuizDatabase::class.java,
            name = QUIZ_DATABASE_NAME
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideQuizDao(
        quizDatabase: QuizDatabase
    ): QuizDao = quizDatabase.quizDao
}