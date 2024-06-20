package com.tech.quiz_app_mvvm.quiz

sealed class EventQuizScreen {
    data class GetQuizzes(
        val numOfQuizzes: Int,
        val category: Int,
        val difficulty: String,
        val type: String
    ) : EventQuizScreen()
}

