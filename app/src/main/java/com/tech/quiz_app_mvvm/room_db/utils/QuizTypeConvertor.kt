package com.tech.quiz_app_mvvm.room_db.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tech.quiz_app_mvvm.quiz.StateQuizScreen

class QuizTypeConvertor {
    private val gson = Gson()

    @TypeConverter
    fun fromStateQuizScreen(state: StateQuizScreen): String {
        return gson.toJson(state)
    }

    @TypeConverter
    fun toStateQuizScreen(state: String): StateQuizScreen {
        val type = object : TypeToken<StateQuizScreen>() {}.type
        return gson.fromJson(state, type)
    }
}