package com.tech.quiz_app_mvvm.quiz

import com.tech.quiz_app_mvvm.domain.model.Quiz

data class StateQuizScreen(
    val isLoading : Boolean = false,
    val data : List<Quiz> ?= listOf(),
    val error : String = ""
)