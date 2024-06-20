package com.tech.quiz_app_mvvm.quiz.component

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.DefaultMarqueeDelayMillis
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.utils.Dimens


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizOption(
    optionNumber: String,
    options: String,
    selected: Boolean,
    onOptionClick: () -> Unit,
    onUnselectOption: () -> Unit
) {
    val optionTextColor =
        if (selected) colorResource(id = R.color.blue_grey) else colorResource(id = R.color.black)
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
        if (selectedBox) colorResource(id = R.color.orange)
        else colorResource(id = R.color.blue_grey)
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
                text = options,
                modifier = Modifier.weight(7.1f),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 3,
                color = optionTextColor
            )
            if (selected) {
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

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember {
            MutableInteractionSource()
        }
    ) {
        onClick()
    }
}

@Preview
@Composable
fun QuizOptionPreview() {
    QuizOption(
        optionNumber = "A",
        options = "gahhkj ghdskhsdag jhsda gdkaj ajgdh",
        selected = false,
        onOptionClick = { /*TODO*/ }) {

    }
}
