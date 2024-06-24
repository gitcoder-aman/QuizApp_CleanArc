package com.tech.quiz_app_mvvm.room_db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tech.quiz_app_mvvm.room_db.domain.model.QuizEntity

@Dao
interface QuizDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quizEntity: QuizEntity)

    @Query("SELECT * FROM quiz_table")
    fun getAllQuizRecords() : List<QuizEntity>
}