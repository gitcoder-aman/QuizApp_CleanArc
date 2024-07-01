package com.tech.quiz_app_mvvm.presentation.nav_graph

import android.util.Log

const val ARG_KEY_QUIZ_NUMBER = "ak_quiz_number"
const val ARG_KEY_QUIZ_CATEGORY = "ak_quiz_category"
const val ARG_KEY_QUIZ_DIFFICULTY = "ak_quiz_difficulty"
const val ARG_KEY_QUIZ_TYPE = "ak_quiz_type"
const val NOQ_KEY = "noq_key"
const val CORRECT_ANS_KEY = "correct_ans_key"
const val WRONG_ANS_KEY = "wrong_ans_key"
const val QUIZ_STATE_RESPONSE = "quiz_state_response"
const val IS_ANS_SHOW = "is_ans_show"

sealed class Routes(val route: String) {
    object HomeScreen : Routes(route = "home_screen")
    object ScoreDetailScreen : Routes(route = "score_details_screen")
    object QuizScreen :
        Routes(route = "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}") {

        fun passQuizParams(
            numOfQuizzes: Int,
            category: String,
            difficulty: String,
            type: String
        ): String {

            return "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}"
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_NUMBER}",
                    newValue = numOfQuizzes.toString()
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_CATEGORY}",
                    newValue = category
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_DIFFICULTY}",
                    newValue = difficulty
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_TYPE}",
                    newValue = type
                )

        }


    }

    object ScoreScreen :
        Routes(route = "score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}/{$WRONG_ANS_KEY}/{$IS_ANS_SHOW}") {
        fun passNumOfQuestionsAndCorrectAns(
            numOfQuestion: Int,
            numOfCorrectAns: Int,
            numOfWrongAns: Int,
            isAnsShow: Boolean
        ): String {
            Log.d("@@isAnsShow", "Routes: $isAnsShow")

            return "score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}/{$WRONG_ANS_KEY}/{$IS_ANS_SHOW}"
                .replace("{$NOQ_KEY}", numOfQuestion.toString())
                .replace("{$CORRECT_ANS_KEY}", numOfCorrectAns.toString())
                .replace("{$WRONG_ANS_KEY}", numOfWrongAns.toString())
                .replace("{$IS_ANS_SHOW}",isAnsShow.toString())

        }
    }

    object AnsShowScreen : Routes(route = "quiz_state_screen/{$QUIZ_STATE_RESPONSE}") {
        fun passQuizStateResponse(quizStateResponse: String): String {
            Log.d("@@quizStateResponse", "Routes: $quizStateResponse")

            Log.d(
                "@@quizStateResponse", "Routes Return: ${
                    "quiz_state_screen/{$QUIZ_STATE_RESPONSE}".replace(
                        "{$QUIZ_STATE_RESPONSE}", quizStateResponse
                    )
                }"
            )
            return "quiz_state_screen/{$QUIZ_STATE_RESPONSE}".replace(
                "{$QUIZ_STATE_RESPONSE}", quizStateResponse
            )
        }
    }

}