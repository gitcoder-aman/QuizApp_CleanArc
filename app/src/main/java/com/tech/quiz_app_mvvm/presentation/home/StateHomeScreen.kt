package com.tech.quiz_app_mvvm.presentation.home

data class StateHomeScreen(
    val numberOfQuiz : Int = 10,
    val category: String = "General Knowledge",
    val difficulty : String = "Easy",
    val type : String = "Multiple Choice"
)
