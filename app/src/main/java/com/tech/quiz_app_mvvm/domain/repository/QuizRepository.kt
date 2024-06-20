package com.tech.quiz_app_mvvm.domain.repository

import com.tech.quiz_app_mvvm.domain.model.Quiz

interface QuizRepository {

    suspend fun getQuizzes(amount : Int,category : Int,difficulty : String,type : String) : List<Quiz>
}