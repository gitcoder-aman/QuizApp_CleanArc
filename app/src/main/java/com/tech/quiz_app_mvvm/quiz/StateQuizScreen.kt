package com.tech.quiz_app_mvvm.quiz

import com.tech.quiz_app_mvvm.domain.model.Quiz

data class StateQuizScreen(
    val isLoading : Boolean = false,
    val quizState : List<QuizState> = listOf(),
    val error : String = "",
    val rightAnswer : Int = 0,
    val wrongAnswer : Int = 0
)
data class QuizState(
    val quiz: Quiz ?= null,
    val shuffleOptions : List<String> = emptyList(),
    val selectedOptions : Int ?= -1
)