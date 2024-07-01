package com.tech.quiz_app_mvvm.presentation.nav_graph

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tech.quiz_app_mvvm.presentation.home.HomeScreen
import com.tech.quiz_app_mvvm.quiz.QuizScreen
import com.tech.quiz_app_mvvm.presentation.home.HomeScreenViewModel
import com.tech.quiz_app_mvvm.presentation.score.ScoreDetailScreen
import com.tech.quiz_app_mvvm.presentation.score.ScoreScreen
import com.tech.quiz_app_mvvm.quiz.QuizViewModel
import com.tech.quiz_app_mvvm.quiz.StateQuizScreen
import com.tech.quiz_app_mvvm.room_db.presentation.QuizRoomViewModel
import com.tech.quiz_app_mvvm.room_db.presentation.QuizSaveRecordState
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Composable
fun SetNavGraph() {
    val navController = rememberNavController()
    val quizViewModel: QuizViewModel = hiltViewModel()
    val state by quizViewModel.quizList.collectAsState()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
        composable(route = Routes.HomeScreen.route) {
            val viewModel: HomeScreenViewModel = hiltViewModel()
            val state by viewModel.homeState.collectAsState()

            HomeScreen(
                state = state,
                event = viewModel::onEvent,
                navController = navController
            )
        }
        composable(route = Routes.ScoreDetailScreen.route) {

            val quizRoomViewModel: QuizRoomViewModel = hiltViewModel()
            val recordState by quizRoomViewModel.allQuizRecords.observeAsState(QuizSaveRecordState.Loading)

            Log.d("@@quizViewmodel", "SetNavGraph: $recordState")


            ScoreDetailScreen(navController, recordState)
        }
        composable(
            route = Routes.QuizScreen.route, arguments = listOf(
                navArgument(ARG_KEY_QUIZ_NUMBER) { type = NavType.IntType },
                navArgument(ARG_KEY_QUIZ_CATEGORY) { type = NavType.StringType },
                navArgument(ARG_KEY_QUIZ_DIFFICULTY) { type = NavType.StringType },
                navArgument(ARG_KEY_QUIZ_TYPE) { type = NavType.StringType },
            )
        ) {
            val numOfQuizzes = it.arguments?.getInt(ARG_KEY_QUIZ_NUMBER)
            val category = it.arguments?.getString(ARG_KEY_QUIZ_CATEGORY)
            val difficulty = it.arguments?.getString(ARG_KEY_QUIZ_DIFFICULTY)
            val type = it.arguments?.getString(ARG_KEY_QUIZ_TYPE)

            QuizScreen(
                numOfQuiz = numOfQuizzes!!,
                quizCategory = category!!,
                quizDifficulty = difficulty!!,
                quizType = type!!,
                event = quizViewModel::onEvent,
                state = state,
                isAnsShow = false,
                navController = navController
            )
        }
        composable(
            route = Routes.ScoreScreen.route,
            arguments = listOf(
                navArgument(NOQ_KEY) { type = NavType.IntType },
                navArgument(CORRECT_ANS_KEY) { type = NavType.IntType },
                navArgument(WRONG_ANS_KEY) { type = NavType.IntType },
                navArgument(IS_ANS_SHOW){type = NavType.BoolType}
            )
        ) {
            val numOfQuestions = it.arguments?.getInt(NOQ_KEY)
            val numOfCorrectAns = it.arguments?.getInt(CORRECT_ANS_KEY)
            val numOfWrongAns = it.arguments?.getInt(WRONG_ANS_KEY)
            val isAnsShow = it.arguments?.getBoolean(IS_ANS_SHOW)


            Log.d("@@isAnsShow", "SetNavGraph: $isAnsShow")

            ScoreScreen(
                numOfQuestions = numOfQuestions!!,
                numOfCorrectAns = numOfCorrectAns!!,
                numOfWrongAns = numOfWrongAns!!,
                state = state,
                isAnsShow = isAnsShow,
                navController = navController
            )
        }
        composable(
            route = Routes.AnsShowScreen.route,
            arguments = listOf(navArgument(QUIZ_STATE_RESPONSE) { type = NavType.StringType })
        ) {
            val quizRecordStateString = it.arguments?.getString(QUIZ_STATE_RESPONSE)
            Log.d("@@quizStateResponse", "SetNavGraph String: $quizRecordStateString")
            val quizRecordStateJson = quizRecordStateString?.let { Json.decodeFromString<StateQuizScreen>(it) } ?: StateQuizScreen()

            QuizScreen(
                numOfQuiz = quizRecordStateJson.quizState.size,
                quizCategory = quizRecordStateJson.quizState[0].quiz?.category?.replace("+"," ")!!,
                quizDifficulty = quizRecordStateJson.quizState[0].quiz?.difficulty!!,
                quizType = quizRecordStateJson.quizState[0].quiz?.type!!,
                event = {},
                state = quizRecordStateJson,
                isAnsShow = true,
                navController = navController
            )
        }
    }
}