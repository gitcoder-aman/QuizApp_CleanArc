package com.tech.quiz_app_mvvm.data.remote.dto

import com.tech.quiz_app_mvvm.domain.model.Quiz

data class QuizResponse(
    val response_code: Int,
    val results: List<Quiz>
)