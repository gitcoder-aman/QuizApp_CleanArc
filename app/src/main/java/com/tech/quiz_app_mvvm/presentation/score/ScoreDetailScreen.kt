package com.tech.quiz_app_mvvm.presentation.score

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.presentation.score.component.ScoreInterface
import com.tech.quiz_app_mvvm.quiz.component.ErrorComposableScreen
import com.tech.quiz_app_mvvm.quiz.component.noRippleClickable
import com.tech.quiz_app_mvvm.room_db.presentation.QuizSaveRecordState
import com.tech.quiz_app_mvvm.ui.theme.LightOrangeColor
import com.tech.quiz_app_mvvm.ui.theme.LightSkyColor
import com.tech.quiz_app_mvvm.ui.theme.LightVioletColor
import com.tech.quiz_app_mvvm.ui.theme.OrangeColor
import com.tech.quiz_app_mvvm.ui.theme.SkyColor
import com.tech.quiz_app_mvvm.ui.theme.VioletColor
import com.tech.quiz_app_mvvm.utils.Dimens

@Composable
fun ScoreDetailScreen(
    navController: NavController,
    recordState: QuizSaveRecordState
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mid_night_blue))
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(Dimens.SmallPadding)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .shadow(shape = CircleShape, elevation = 8.dp)
                    .background(colorResource(id = R.color.blue_grey)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "back_arrow",
                    tint = Color.Black,
                    modifier = Modifier
                        .noRippleClickable {
                            navController.navigateUp()
                        }
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            Text(
                text = stringResource(R.string.your_scores),
                color = colorResource(id = R.color.blue_grey),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                fontSize = Dimens.LargeTextSize
            )
        }

        ScoreDetailContent(recordState)
    }
}

@Composable
fun ScoreDetailContent(recordState: QuizSaveRecordState) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 170.dp)
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.blue_grey), shape = RoundedCornerShape(
                        topStart = Dimens.ExtraLargeCornerRadius,
                        topEnd = Dimens.ExtraLargeCornerRadius
                    )
                )
                .padding(
                    top = Dimens.SmallPadding,
                    start = Dimens.SmallPadding,
                    end = Dimens.SmallPadding
                )
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

            when (recordState) {
                is QuizSaveRecordState.Loading -> {
                    CircularProgressIndicator()
                }

                is QuizSaveRecordState.Success -> {
                    val listQuizRecords = (recordState as QuizSaveRecordState.Success).data
                    if(listQuizRecords.isNotEmpty()) {
                        LazyColumn {
                            items(listQuizRecords) { quizRecord ->

                                val listOfLightColor = listOf(
                                    LightOrangeColor, LightSkyColor, LightVioletColor
                                ).random()

                                val listOfColor = mapOf(
                                    LightOrangeColor to OrangeColor,
                                    LightSkyColor to SkyColor,
                                    LightVioletColor to VioletColor
                                )
                                ScoreInterface(
                                    countNumber = quizRecord.id.toString(),
                                    category = quizRecord.state.quizState[0].quiz?.category!!,
                                    totalRightAnswer = quizRecord.numOfRightAnswer.toString(),
                                    totalWrongAnswer = quizRecord.numOfWrongAnswer.toString(),
                                    nonAttemptedQuestion = quizRecord.unattemptedQuestion.toString(),
                                    timeDuration = "30s",
                                    backgroundColor = listOfLightColor,
                                    iconColor = listOfColor[listOfLightColor]!!
                                )
                            }
                        }
                    }else{
                        ErrorComposableScreen(error = stringResource(R.string.please_play_the_quiz))
                    }
                }

                is QuizSaveRecordState.Error -> {
                    val errorMessage = recordState.message
                    ErrorComposableScreen(error = errorMessage)
                }
            }
        }
    }
}


@Preview
@Composable
fun ScoreDetailScreenPreview() {
    ScoreDetailScreen(NavController(LocalContext.current), QuizSaveRecordState.Loading)
}