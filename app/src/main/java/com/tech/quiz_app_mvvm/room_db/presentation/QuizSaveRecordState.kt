package com.tech.quiz_app_mvvm.room_db.presentation

import com.tech.quiz_app_mvvm.room_db.domain.model.QuizEntity

sealed class QuizSaveRecordState {
    object Loading : QuizSaveRecordState()
    data class Success(val data: List<QuizEntity>) : QuizSaveRecordState()
    data class Error(val message: String) : QuizSaveRecordState()
}