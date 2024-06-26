package com.tech.quiz_app_mvvm.quiz.component

import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.DefaultMarqueeDelayMillis
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.quiz.QuizState
import com.tech.quiz_app_mvvm.ui.theme.LightGreenColor
import com.tech.quiz_app_mvvm.ui.theme.LightRedColor
import com.tech.quiz_app_mvvm.utils.Dimens


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizOption(
    optionNumber: String,
    options: String,
    selected: Boolean,
    isAnsShow: Boolean,
    quizState: QuizState,
    onOptionClick: () -> Unit,
    onUnselectOption: () -> Unit,
) {

    var optionTextColor: Color? = null
    val selectedOption = quizState.selectedOptions
    var findCorrectAns = remember {
        mutableStateOf(false)
    }

    if (isAnsShow && selected) {
        optionTextColor =
            if (quizState.shuffleOptions[selectedOption!!] == quizState.quiz?.correct_answer) {
                colorResource(
                    id = R.color.mid_night_blue
                )
            } else {
                colorResource(id = R.color.black)
            }
    } else {
        optionTextColor =
            if (selected) colorResource(id = R.color.blue_grey) else colorResource(id = R.color.black)

    }
    val transition = updateTransition(targetState = selected, label = "selected")
    val startColor by transition.animateColor(
        transitionSpec = {
            tween(
                durationMillis = DefaultMarqueeDelayMillis,
                easing = LinearEasing
            )
        },
        label = "startColor"
    ) { selectedBox ->
        if (selectedBox && !isAnsShow) {
            colorResource(id = R.color.orange)
        } else if (selectedBox && isAnsShow && quizState.shuffleOptions[selectedOption!!] == quizState.quiz?.correct_answer) {
            LightGreenColor
        } else if (selectedBox && isAnsShow && quizState.shuffleOptions[selectedOption!!] != quizState.quiz?.correct_answer) {
            LightRedColor
        } else {

            var correctAnsIndex = findCorrectAns(quizState = quizState,findCorrectAns)
            if (quizState.shuffleOptions[correctAnsIndex].replace("&quot", "\"")
                    .replace("&#039;", "\"") == options && isAnsShow) {
                LightGreenColor
            } else {
                colorResource(id = R.color.blue_grey)
            }

        }
    }

    Box(
        modifier = Modifier
            .noRippleClickable { onOptionClick() }
            .fillMaxWidth()
            .height(Dimens.MediumBoxHeight)
            .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
            .background(
                color = startColor,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (!selected) {
                Box(
                    modifier = Modifier
                        .size(Dimens.SmallCircleShape)
                        .shadow(
                            10.dp, CircleShape, true, colorResource(id = R.color.black)
                        )
                        .weight(1.5f)
                        .clip(CircleShape)
                        .background(colorResource(id = R.color.orange)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = optionNumber,
                        fontWeight = FontWeight.Bold,
                        fontSize = Dimens.MediumTextSize,
                        color = colorResource(id = R.color.blue_grey),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(CircleShape)
                        .size(Dimens.SmallCircleShape)
                )
            }
            Spacer(
                modifier = Modifier
                    .width(Dimens.SmallSpacerWidth)
                    .weight(0.6f)
            )

            Text(
                text = options.replace("+", " "),
                modifier = Modifier.weight(7.1f),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 3,
                color = optionTextColor
            )
            if (selected && !isAnsShow) {
                Box(
                    modifier = Modifier
                        .weight(1.5f)
                        .shadow(10.dp, CircleShape, true, colorResource(id = R.color.black))
                        .clip(CircleShape)
                        .size(Dimens.SmallCircleShape)
                        .background(colorResource(id = R.color.blue_grey)),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { onUnselectOption() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "close",
                            tint = colorResource(
                                id = R.color.orange
                            )
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .weight(1.5f)
                        .clip(CircleShape)
                        .size(Dimens.SmallCircleShape)
                )
            }
        }
    }
}

@Composable
fun findCorrectAns(quizState: QuizState, findCorrectAns: MutableState<Boolean>): Int {
//    var color: Color? = null
    var index = -1
    for (i in quizState.shuffleOptions.indices) {
        if (quizState.shuffleOptions[i] == quizState.quiz?.correct_answer) {
//            color = LightGreenColor
            Log.d("@correctAsIndex", "QuizOption: ${quizState.shuffleOptions[i]}")
            Log.d("@correctAsIndex", "QuizOption: ${quizState.quiz.correct_answer}")
            findCorrectAns.value = true

            index = i
        }
    }
//    val animatedColor by animateColorAsState(color!!, label = "")
    return index
}

@Preview
@Composable
fun QuizOptionPreview() {
    QuizOption(
        optionNumber = "A",
        options = "gahhkj ghdskhsdag jhsda gdkaj ajgdh",
        selected = false,
        isAnsShow = false,
        quizState = QuizState(),
        onOptionClick = { /*TODO*/ },
    ) {

    }
}
