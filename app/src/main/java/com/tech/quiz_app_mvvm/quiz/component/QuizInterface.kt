package com.tech.quiz_app_mvvm.quiz.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.quiz.QuizState
import com.tech.quiz_app_mvvm.utils.Dimens

@Composable
fun QuizInterface(
    onOptionSelected: (Int) -> Unit,
    qNumber: Int,
    quizState: QuizState,
    modifier: Modifier = Modifier
) {
    val question = quizState.quiz?.question!!.replace("&quot", "\"").replace("&#039;", "\"")
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.wrapContentHeight()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$qNumber",
                    modifier = Modifier.weight(1f),
                    color = colorResource(id = R.color.blue_grey),
                    fontSize = Dimens.SmallTextSize,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = question,
                    color = colorResource(id = R.color.blue_grey),
                    modifier = Modifier.weight(9f),
                    fontSize = Dimens.SmallTextSize,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            Column(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                var options: List<Pair<String, String>> = emptyList()
                if (quizState.quiz.type == "multiple") {
                    options = listOf(
                        "A" to quizState.shuffleOptions[0].replace("&quot", "\"")
                            .replace("&#039;", "\""),
                        "B" to quizState.shuffleOptions[1].replace("&quot", "\"")
                            .replace("&#039;", "\""),
                        "C" to quizState.shuffleOptions[2].replace("&quot", "\"")
                            .replace("&#039;", "\""),
                        "D" to quizState.shuffleOptions[3].replace("&quot", "\"")
                            .replace("&#039;", "\"")
                    )
                } else {
                    options = listOf(
                        "A" to quizState.shuffleOptions[0].replace("&quot", "\"")
                            .replace("&#039;", "\""),

                        "B" to quizState.shuffleOptions[1].replace("&quot", "\"")
                            .replace("&#039;", "\""),
                    )
                }
                Column {
                    options.forEachIndexed { index, (optionNumber: String, optionText: String) ->
                        if (optionText.isNotEmpty()) {
                            QuizOption(
                                optionNumber = optionNumber,
                                options = optionText,
                                selected = quizState.selectedOptions == index,
                                onOptionClick = { onOptionSelected(index) },
                                onUnselectOption = { onOptionSelected(-1) }
                            )
                        }
                        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
                    }
                }
                Spacer(modifier = Modifier.height(Dimens.ExtraLargeSpacerHeight))
            }
        }
    }
}


@Preview
@Composable
fun QuizInterfacePreview() {
    QuizInterface(onOptionSelected = {}, quizState = QuizState(), qNumber = 1)
}