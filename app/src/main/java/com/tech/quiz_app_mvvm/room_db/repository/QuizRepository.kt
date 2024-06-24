package com.tech.quiz_app_mvvm.room_db.repository

import androidx.lifecycle.LiveData
import com.tech.quiz_app_mvvm.room_db.dao.QuizDao
import com.tech.quiz_app_mvvm.room_db.domain.model.QuizEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val quizDao: QuizDao
) {
//    val allQuizRecords: LiveData<List<QuizEntity>> =  quizDao.getAllQuizRecords()

    suspend fun getAllQuizRecords(): List<QuizEntity> {
        return withContext(Dispatchers.IO) {
            quizDao.getAllQuizRecords()
        }
    }
    suspend fun insert(quizEntity: QuizEntity) {
        withContext(Dispatchers.IO) {
            quizDao.insert(quizEntity)
        }
    }
}