package com.tech.quiz_app_mvvm.room_db.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.quiz_app_mvvm.room_db.domain.model.QuizEntity
import com.tech.quiz_app_mvvm.room_db.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizRoomViewModel @Inject constructor(
    private var quizRepository: QuizRepository,
) : ViewModel() {

    private val _allQuizRecords = MutableLiveData<QuizSaveRecordState>()
    val allQuizRecords: LiveData<QuizSaveRecordState> get() = _allQuizRecords

    init {
        fetchAllQuizRecords()
    }

    private fun fetchAllQuizRecords() {

        _allQuizRecords.value = QuizSaveRecordState.Loading
        viewModelScope.launch {
            try {
                val quizRecords = quizRepository.getAllQuizRecords()
                _allQuizRecords.value = QuizSaveRecordState.Success(quizRecords)
            } catch (e: Exception) {
                _allQuizRecords.value = QuizSaveRecordState.Error(e.message ?: "Insert Error")
            }
        }
    }

    fun insertQuizRecord(quizEntity: QuizEntity) = viewModelScope.launch {
        try {
            quizRepository.insert(quizEntity)
            fetchAllQuizRecords()
        } catch (e: Exception) {
            _allQuizRecords.value = QuizSaveRecordState.Error(e.message ?: "Insert Error")
        }
    }
}