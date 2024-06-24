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
import kotlin.math.log

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizzesUseCases: GetQuizzesUseCases
) : ViewModel() {

    private val _quizList = MutableStateFlow(StateQuizScreen())
    val quizList = _quizList
    fun onEvent(event: EventQuizScreen) {
        when (event) {
            is EventQuizScreen.GetQuizzes -> {
                getQuizzes(
                    amount = event.numOfQuizzes,
                    category = event.category,
                    difficulty = event.difficulty,
                    type = event.type
                )
            }

            is EventQuizScreen.SetOptionSelected -> {
                updateQuizStateList(event.quizStateIndex, event.selectedOption)
            }
        }
    }

    //contain the selected options
    private fun updateQuizStateList(quizStateIndex: Int, selectedOption: Int) {

        val updatedQuizStateList = mutableListOf<QuizState>()
        _quizList.value.quizState.forEachIndexed { index, quizState ->
            updatedQuizStateList.add(
                if (quizStateIndex == index) {
                    quizState.copy(selectedOptions = selectedOption)
                } else {
                    quizState
                }
            )
        }
        _quizList.value = _quizList.value.copy(quizState = updatedQuizStateList)

        //updateScore
        updateScore(_quizList.value.quizState[quizStateIndex])
    }

    private fun updateScore(quizState: QuizState) {

        if (quizState.selectedOptions != -1) {
            val correctAnswer = quizState.quiz?.correct_answer
            val selectedAnswer = quizState.selectedOptions?.let { selectedOptions ->
                quizState.shuffleOptions[selectedOptions].replace("&quot", "\"")
                    .replace("&#039;", "\"")
            }
            Log.d(
                "@@score",
                "SelectedAnswer: $selectedAnswer"
            )
            if (correctAnswer == selectedAnswer) {
                val previousRightAnswer = _quizList.value.rightAnswer
                _quizList.value = quizList.value.copy(
                    rightAnswer = previousRightAnswer + 1
                )
            } else {
                Log.d("@@score_wrong", "updateScore: ${_quizList.value.wrongAnswer}")
                val previousWrongAnswer = _quizList.value.wrongAnswer
                _quizList.value = quizList.value.copy(
                    wrongAnswer = previousWrongAnswer + 1)
                Log.d("@@score_wrong", "updateScore: ${_quizList.value.wrongAnswer}")

            }
//            else if(quizState.selectedOptions?.equals(-1) == true){//when correct ans not matched
//                val previousRightAnswer = _quizList.value.rightAnswer
//                _quizList.value = quizList.value.copy(
//                    rightAnswer = previousRightAnswer - 1
//                )
//            }else if(correctAnswer != selectedAnswer){
//                val previousWrongAnswer = _quizList.value.wrongAnswer
//                _quizList.value = quizList.value.copy(
//                    wrongAnswer = previousWrongAnswer + 1
//                )
//            }
        }
    }

    private fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ) {
        viewModelScope.launch {
            getQuizzesUseCases(amount, category, difficulty, type).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _quizList.value = StateQuizScreen(isLoading = true)
                    }

                    is Resource.Success -> {
                        if (resource.data?.response_code == 0) {
                            val listOfQuizState: List<QuizState> =
                                getListOfQuizState(resource.data.results)
                            _quizList.value = StateQuizScreen(quizState = listOfQuizState)
                        } else {
                            _quizList.value =
                                StateQuizScreen(error = "No Data Found,Please No of Questions taking less.")
                        }
                    }

                    is Resource.Error -> {
                        _quizList.value = StateQuizScreen(error = resource.message.toString())
                    }
                }
            }
        }
    }

    private fun getListOfQuizState(data: List<Quiz>?): List<QuizState> {

        val listOfQuizState = mutableListOf<QuizState>()
        for (quiz in data!!) {
            val shuffledOptions = mutableListOf<String>().apply {
                add(quiz.correct_answer)
                addAll(quiz.incorrect_answers)
                shuffle()
            }
            Log.d("@@quiz", "getListOfQuizState: $shuffledOptions")
            listOfQuizState.add(QuizState(quiz, shuffledOptions, -1))
        }
        return listOfQuizState
    }
}