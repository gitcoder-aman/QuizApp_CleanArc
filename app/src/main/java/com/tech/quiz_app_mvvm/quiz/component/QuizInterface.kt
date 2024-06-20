package com.tech.quiz_app_mvvm.quiz.component

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.DefaultMarqueeDelayMillis
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.utils.Dimens

@Composable
fun QuizInterface(
    onOptionSelected: (Int) -> Unit,
    qNumber: Int,
    modifier: Modifier = Modifier
) {
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
                    text = "Which cartoon do you see?",
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
                val options = listOf(
                    "A" to "Doramon",
                    "B" to "Oggy",
                    "C" to "Ben 10",
                    "D" to "Tom and Jerry"
                )
                Column {
                    options.forEachIndexed { index, (optionNumber: String, optionText: String) ->
                        if (optionText.isNotEmpty()) {
                            QuizOption(
                                optionNumber = optionNumber,
                                options = optionText,
                                selected = false,
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
    QuizInterface(onOptionSelected = {}, qNumber = 1)
}