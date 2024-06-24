package com.tech.quiz_app_mvvm.presentation.nav_graph

const val ARG_KEY_QUIZ_NUMBER = "ak_quiz_number"
const val ARG_KEY_QUIZ_CATEGORY = "ak_quiz_category"
const val ARG_KEY_QUIZ_DIFFICULTY = "ak_quiz_difficulty"
const val ARG_KEY_QUIZ_TYPE = "ak_quiz_type"
const val NOQ_KEY = "noq_key"
const val CORRECT_ANS_KEY = "correct_ans_key"
const val WRONG_ANS_KEY = "wrong_ans_key"

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
    object ScoreScreen : Routes(route = "score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}/{$WRONG_ANS_KEY}"){
        fun passNumOfQuestionsAndCorrectAns(numOfQuestion : Int,numOfCorrectAns : Int,numOfWrongAns : Int) : String{
            return "score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}/{$WRONG_ANS_KEY}"
                .replace("{$NOQ_KEY}",numOfQuestion.toString())
                .replace("{$CORRECT_ANS_KEY}",numOfCorrectAns.toString())
                .replace("{$WRONG_ANS_KEY}",numOfWrongAns.toString())

        }
    }

}