package com.tech.quiz_app_mvvm.quiz

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.presentation.common.QuizAppBar
import com.tech.quiz_app_mvvm.utils.Constants
import com.tech.quiz_app_mvvm.utils.Dimens

@Composable
fun QuizScreen(
    numOfQuiz: Int,
    quizCategory: String,
    quizDifficulty: String,
    quizType: String,
    event: (EventQuizScreen) -> Unit,
    state: StateQuizScreen
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

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

        }
        Column(
            modifier = Modifier
                .padding(Dimens.VerySmallPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Questions: $numOfQuiz", color = colorResource(id = R.color.blue_grey))
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
            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            //Quiz Interface

        }
    }

}

//@Preview
//@Composable
//fun QuizScreenPreview() {
//    QuizScreen(numOfQuiz = 10 , quizCategory = "Science", quizDifficulty = "Hard")
//}

