package com.tech.quiz_app_mvvm.room_db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tech.quiz_app_mvvm.room_db.dao.QuizDao
import com.tech.quiz_app_mvvm.room_db.domain.model.QuizEntity
import com.tech.quiz_app_mvvm.room_db.utils.QuizTypeConvertor

@Database(entities = [QuizEntity::class], version = 1, exportSchema = false)
@TypeConverters(QuizTypeConvertor::class)
abstract class QuizDatabase : RoomDatabase(){

    abstract val quizDao : QuizDao
}