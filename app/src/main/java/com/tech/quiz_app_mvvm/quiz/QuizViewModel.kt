package com.tech.quiz_app_mvvm.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.quiz_app_mvvm.common.Resource
import com.tech.quiz_app_mvvm.domain.model.Quiz
import com.tech.quiz_app_mvvm.domain.usecases.GetQuizzesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizzesUseCases: GetQuizzesUseCases
): ViewModel() {

   private val _quizList = MutableStateFlow(StateQuizScreen())
    val quizList = _quizList
    fun onEvent(event : EventQuizScreen){
        when(event){
            is EventQuizScreen.GetQuizzes -> {
                getQuizzes(
                    amount = event.numOfQuizzes,
                    category = event.category,
                    difficulty = event.difficulty,
                    type = event.type
                )
            }
        }
    }
    private fun getQuizzes(
        amount : Int,
        category: Int,
        difficulty : String,
        type : String
    ){
        viewModelScope.launch {
            getQuizzesUseCases(amount, category, difficulty, type).collect { resource ->
                when(resource){
                    is Resource.Loading->{
                        _quizList.value = StateQuizScreen(isLoading = true)
                    }
                    is Resource.Success->{
                        for (quiz:Quiz in resource.data!!){
                            Log.d("@@quiz", "getQuizzes: ${quiz.toString()}")
                        }
                        _quizList.value = StateQuizScreen(data = resource.data)
                    }
                    is Resource.Error->{
                        _quizList.value = StateQuizScreen(error = resource.message.toString())
                    }
                }
            }
        }
    }
}