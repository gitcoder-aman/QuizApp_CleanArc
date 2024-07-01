package com.tech.quiz_app_mvvm.quiz

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.presentation.common.ButtonBox
import com.tech.quiz_app_mvvm.presentation.common.QuizAppBar
import com.tech.quiz_app_mvvm.presentation.nav_graph.Routes
import com.tech.quiz_app_mvvm.quiz.component.ErrorComposableScreen
import com.tech.quiz_app_mvvm.quiz.component.QuizInterface
import com.tech.quiz_app_mvvm.quiz.component.ShimmerEffectBooleanQuiz
import com.tech.quiz_app_mvvm.quiz.component.ShimmerEffectMultipleQuiz
import com.tech.quiz_app_mvvm.utils.Constants
import com.tech.quiz_app_mvvm.utils.Dimens
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(
    numOfQuiz: Int,
    quizCategory: String,
    quizDifficulty: String,
    quizType: String,
    event: (EventQuizScreen) -> Unit,
    state: StateQuizScreen,
    isAnsShow: Boolean,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        BackHandler {
            navController.navigateUp()
        }
        LaunchedEffect(key1 = Unit) {
            val difficulty = when (quizDifficulty) {
                "Medium" -> "medium"
                "Hard" -> "hard"
                else -> "easy"
            }
            val type = when (quizType) {
                "Multiple Choice" -> "multiple"
                else -> "boolean"
            }
            event(
                EventQuizScreen.GetQuizzes(
                    numOfQuiz,
                    Constants.categoriesMap[quizCategory]!!,
                    difficulty,
                    type
                )
            )
        }
        QuizAppBar(quizCategory = quizCategory) {
            navController.navigateUp()
        }
        Column {
            if (!state.isLoading) {
                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Questions: $numOfQuiz",
                        color = colorResource(id = R.color.blue_grey)
                    )
                    Text(text = quizDifficulty, color = colorResource(id = R.color.blue_grey))
                }
                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.VerySmallPadding)
                        .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                        .background(colorResource(id = R.color.blue_grey))
                )
            }

            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            if (quizFetched(state, quizType)) {

                for (i in state.quizState) {
                    Log.d("@@fetchQuiz", "QuizScreen: ${i.quiz}")
                }
                val pagerState = rememberPagerState {
                    state.quizState.size
                }

                HorizontalPager(state = pagerState) { index ->
                    //Quiz Interface
                    QuizInterface(
                        modifier = Modifier.weight(1f),
                        onOptionSelected = { selectedIndex ->
                            event(EventQuizScreen.SetOptionSelected(index, selectedIndex))
                        },
                        quizState = state.quizState[index],
                        qNumber = index + 1,
                        isAnsShow = isAnsShow
                    )
                }

                val buttonText by remember {
                    derivedStateOf {
                        when (pagerState.currentPage) {
                            0 -> {
                                listOf("", "Next")
                            }

                            state.quizState.size - 1 -> {
                                listOf("Previous", if (!isAnsShow) "Submit" else "Check Score")
                            }

                            else -> {
                                listOf("Previous", "Next")
                            }
                        }
                    }
                }
                Log.d("@@quiz_buttonText", "QuizScreen: ${buttonText[0]}")
                Log.d("@@quiz_buttonText", "QuizScreen: ${buttonText[1]}")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Dimens.MediumPadding)
                        .navigationBarsPadding()
                ) {
                    val scope = rememberCoroutineScope()
                    if (buttonText[0].isNotEmpty()) {
                        ButtonBox(
                            text = "Previous",
                            padding = Dimens.SmallPadding,
                            fraction = 0.43f,
                            fontSize = Dimens.SmallTextSize,
                            borderColor = colorResource(id = R.color.mid_night_blue),
                        ) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    } else {
                        ButtonBox(
                            text = "",
                            fraction = 0.43f,
                            fontSize = Dimens.SmallTextSize,
                            containerColor = colorResource(id = R.color.mid_night_blue),
                        ) {
                            //Nothing
                        }
                    }

                    ButtonBox(
                        text = buttonText[1],
                        containerColor = if (buttonText[1] == "Next") colorResource(
                            id = R.color.dark_slate_blue
                        ) else colorResource(id = R.color.orange),
                        padding = Dimens.SmallPadding,
                        fraction = 1f,
                        fontSize = Dimens.SmallTextSize,
                        textColor = colorResource(id = R.color.blue_grey),
                        borderColor = colorResource(id = R.color.blue_grey),
                    ) {
                        if (pagerState.currentPage == state.quizState.size - 1) {
                            Log.d("@@isAnsShow", "QuizScreen: $isAnsShow")

                            //TODO
                            navController.navigate(
                                Routes.ScoreScreen.passNumOfQuestionsAndCorrectAns(
                                    numOfQuestion = state.quizState.size,
                                    numOfCorrectAns = state.rightAnswer,
                                    numOfWrongAns = state.wrongAnswer,
                                    isAnsShow = isAnsShow
                                )
                            )

                        }else {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun quizFetched(state: StateQuizScreen, quizType: String): Boolean {

    return when {
        state.isLoading -> {
            if (quizType == "Multiple Choice") {
                ShimmerEffectMultipleQuiz()
            } else {
                ShimmerEffectBooleanQuiz()
            }
            false
        }

        state.quizState.isNotEmpty() -> {
            true
        }

        else -> {
            ErrorComposableScreen(state.error)
            false
        }
    }
}

@Preview
@Composable
fun QuizScreenPreview() {
    QuizScreen(
        numOfQuiz = 10,
        quizCategory = "Science",
        quizDifficulty = "Hard",
        quizType = "Easy",
        event = {},
        state = StateQuizScreen(),
        isAnsShow = false,
        navController = NavController(
            LocalContext.current
        )
    )
}

@Preview
@Composable
fun ErrorComposableScreenPreview() {
    ErrorComposableScreen(error = "Something went wrong!")
}

