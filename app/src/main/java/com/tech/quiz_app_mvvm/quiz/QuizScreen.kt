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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.presentation.common.ButtonBox
import com.tech.quiz_app_mvvm.presentation.common.QuizAppBar
import com.tech.quiz_app_mvvm.presentation.nav_graph.Routes
import com.tech.quiz_app_mvvm.presentation.nav_graph.goToHome
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
                        qNumber = index + 1
                    )
                }

                val buttonText by remember {
                    derivedStateOf {
                        when (pagerState.currentPage) {
                            0 -> {
                                listOf("", "Next")
                            }

                            state.quizState.size - 1 -> {
                                listOf("Previous", "Submit")
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
                            borderColor = colorResource(id = R.color.mid_night_blue)
                        ) {
                            //Nothing
                        }
                    }

                    ButtonBox(
                        text = buttonText[1],
                        containerColor = if (buttonText[1] == "Submit") colorResource(id = R.color.orange) else colorResource(
                            id = R.color.dark_slate_blue
                        ),
                        padding = Dimens.SmallPadding,
                        fraction = 1f,
                        fontSize = Dimens.SmallTextSize,
                        textColor = colorResource(id = R.color.white),
                    ) {
                        if (pagerState.currentPage == state.quizState.size - 1) {
                            //TODO

                            Log.d(
                                "@@score",
                                "QuizScreen: ${state.score}"
                            )
                            navController.navigate(
                                Routes.ScoreScreen.passNumOfQuestionsAndCorrectAns(
                                    numOfQuestion = state.quizState.size,
                                    numOfCorrectAns = state.score
                                )
                            )
                        }
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
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

@Composable
fun ErrorComposableScreen(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.no_data_found))

            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(Dimens.NoDataFoundLottieAnimationSize),
                iterations = 100
            )
            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

            Text(text = error, style = TextStyle(
                color = colorResource(id = R.color.white),
                fontSize = Dimens.SmallTextSize,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ))
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

