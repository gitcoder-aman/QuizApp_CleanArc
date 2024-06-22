package com.tech.quiz_app_mvvm.data.repository

import com.tech.quiz_app_mvvm.data.remote.dto.QuizApi
import com.tech.quiz_app_mvvm.data.remote.dto.QuizResponse
import com.tech.quiz_app_mvvm.domain.repository.QuizRepository

class QuizRepositoryImpl(
    private val quizApi : QuizApi
) :QuizRepository {
    override suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): QuizResponse {
        return quizApi.getQuizzes(amount, category, difficulty, type)
    }
}