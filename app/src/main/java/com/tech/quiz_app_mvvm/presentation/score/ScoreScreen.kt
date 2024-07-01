package com.tech.quiz_app_mvvm.presentation.score

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.presentation.nav_graph.goToHome
import com.tech.quiz_app_mvvm.quiz.StateQuizScreen
import com.tech.quiz_app_mvvm.room_db.domain.model.QuizEntity
import com.tech.quiz_app_mvvm.room_db.presentation.QuizRoomViewModel
import com.tech.quiz_app_mvvm.utils.Dimens
import java.text.DecimalFormat

@Composable
fun ScoreScreen(
    numOfQuestions: Int,
    numOfCorrectAns: Int,
    numOfWrongAns: Int,
    state: StateQuizScreen,
    navController: NavController,
    isAnsShow: Boolean?
) {

    Log.d("@@isAnsShow", "ScoreScreen: $isAnsShow")
    val scorePercentage = calculatePercentage(numOfCorrectAns, numOfQuestions)

    // State variable to control database insertion
    val isInserted = remember { mutableStateOf(false) }

    if (!isInserted.value) {
        if (isAnsShow == false) {
            SaveQuizRecordInLocalDatabase(
                numOfQuestions,
                numOfCorrectAns,
                numOfWrongAns,
                state,
                scorePercentage, onInserted = { isInserted.value = true }
            )
        }
    }

//    for (i in state.quizState) {
//        Log.d("@@score_state", "ScoreScreen: $i")
//    }
    BackHandler {
        goToHome(navController)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.MediumPadding),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { goToHome(navController) }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "close",
                    tint = colorResource(
                        id = R.color.blue_grey
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                .background(colorResource(id = R.color.blue_grey)),
            contentAlignment = Alignment.Center
        ) {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.congratulation_lottie))

            val annotatedString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("You attempted")
                }
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append(" $numOfQuestions questions")
                }
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append(" and from that")
                }
                withStyle(style = SpanStyle(color = colorResource(id = R.color.green))) {
                    append(" $numOfCorrectAns answers")
                }
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append(" are correct")
                }
                withStyle(style = SpanStyle(color = Color.Red)) {
                    append(" $numOfWrongAns answers")
                }
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append(" are wrong.")
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier.size(Dimens.LargeLottieAnimation),
                    iterations = 100
                )
                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

                Text(
                    text = "Congrats!", color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.MediumTextSize, fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
                Text(
                    text = "$scorePercentage% Score", color = colorResource(id = R.color.green),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.LargeTextSize, fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
                Text(
                    text = "Quiz completed successfully", color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.SmallTextSize, fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
                Text(
                    text = annotatedString, color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.SmallTextSize,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Share with us :",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = Dimens.SmallTextSize
                    )
                    Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))
                    Icon(
                        painter = painterResource(id = R.drawable.instagram),
                        contentDescription = "instagram",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))
                    Icon(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "facebook",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))

                    Icon(
                        painter = painterResource(id = R.drawable.linkedin),
                        contentDescription = "Linkedin",
                        modifier = Modifier.size(40.dp)
                    )

                }
            }
        }

    }
}

@Composable
fun SaveQuizRecordInLocalDatabase(
    numOfQuestions: Int,
    numOfCorrectAns: Int,
    numOfWrongAns: Int,
    state: StateQuizScreen,
    scorePercentage: Double,
    onInserted: () -> Unit
) {
    val quizRoomViewModel: QuizRoomViewModel = hiltViewModel()

    LaunchedEffect(key1 = Unit) {

        val quizEntity = QuizEntity(
            numOfQuestion = numOfQuestions,
            numOfRightAnswer = numOfCorrectAns,
            numOfWrongAnswer = numOfWrongAns,
            state = state,
            scorePercentage = scorePercentage,
            unattemptedQuestion = numOfQuestions - (numOfCorrectAns + numOfWrongAns)
        )
        quizRoomViewModel.insertQuizRecord(quizEntity)
        onInserted()
    }
}

fun calculatePercentage(numOfCorrectAns: Int, numOfQuestions: Int): Double {
    require(numOfCorrectAns >= 0 && numOfQuestions > 0) {
        "Invalid input: numOfCorrectAns must be non-negative and numOfQuestions must be positive"
    }
    val percentage = (numOfCorrectAns.toDouble() / numOfQuestions.toDouble()) * 100.0
    return DecimalFormat("#.##").format(percentage).toDouble()
}

@Preview
@Composable
fun ScoreScreenPreview() {
    ScoreScreen(
        numOfQuestions = 11,
        numOfCorrectAns = 6,
        numOfWrongAns = 4,
        state = StateQuizScreen(),
        navController = NavController(LocalContext.current),
        isAnsShow = false
    )
}