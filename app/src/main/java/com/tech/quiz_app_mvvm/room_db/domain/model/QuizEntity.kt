package com.tech.quiz_app_mvvm.room_db.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tech.quiz_app_mvvm.quiz.StateQuizScreen

@Entity(tableName = "quiz_table")
data class QuizEntity(
    @PrimaryKey(autoGenerate = true) val id : Int= 0,
    val numOfQuestion : Int,
    val numOfRightAnswer : Int,
    val numOfWrongAnswer : Int,
    val unattemptedQuestion : Int,
    val scorePercentage : Double,
    val state : StateQuizScreen,
)
