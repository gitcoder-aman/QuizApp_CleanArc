package com.tech.quiz_app_mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.tech.quiz_app_mvvm.presentation.home.EventHomeScreen
import com.tech.quiz_app_mvvm.presentation.home.StateHomeScreen
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreenViewModel : ViewModel(){

    private val _homeState = MutableStateFlow(StateHomeScreen())
    val homeState = _homeState

    fun onEvent(event : EventHomeScreen){
        when(event){
            is EventHomeScreen.SetNumberOfQuizzes->{
                _homeState.value = homeState.value.copy(numberOfQuiz = event.numberOfQuizzes)
            }
            is EventHomeScreen.SetQuizCategory->{
                _homeState.value = homeState.value.copy(category = event.category)
            }
            is EventHomeScreen.SetQuizDifficulty->{
                _homeState.value = homeState.value.copy(difficulty = event.difficulty)
            }
            is EventHomeScreen.SetQuizType->{
                _homeState.value = homeState.value.copy(type = event.type)
            }
        }
    }
}