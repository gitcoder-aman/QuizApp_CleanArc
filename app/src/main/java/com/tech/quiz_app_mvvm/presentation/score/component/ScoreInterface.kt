package com.tech.quiz_app_mvvm.presentation.score.component

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.ui.theme.LightOrangeColor
import com.tech.quiz_app_mvvm.ui.theme.OrangeColor
import com.tech.quiz_app_mvvm.utils.Dimens

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreInterface(
    countNumber: String = "1",
    category: String = "Science & Computers",
    totalRightAnswer: String = "10",
    totalWrongAnswer: String = "10",
    nonAttemptedQuestion : String = "10",
    timeDuration: String = "30s",
    backgroundColor: Color = LightOrangeColor,
    iconColor: Color = OrangeColor,
) {
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(5.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = backgroundColor,
            contentColor = Color.Black
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.SmallPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberCircleUI(countNumber = countNumber, iconColor = iconColor)

            Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(Dimens.VerySmallPadding)
            ) {
                Row(
                    modifier = Modifier.width(170.dp)
                ) {
                    Text(
                        text = category,
                        color = colorResource(id = R.color.black),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Start,
                        fontSize = Dimens.SmallTextSize,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconWithText(
                        icon = R.drawable.right_icon,
                        text = totalRightAnswer,
                        iconTint = Color.Green
                    )
                    Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))

                    IconWithText(
                        icon = R.drawable.incorrect,
                        text = totalWrongAnswer,
                        iconTint = Color.Red
                    )
                    Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))

                    IconWithText(
                        icon = R.drawable.non_attempt,
                        text = nonAttemptedQuestion,
                        iconTint = Color.Blue
                    )
                    Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))
                }

            }
            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.clock_icon),
                    contentDescription = "clock_icon",
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = timeDuration,
                    color = colorResource(id = R.color.black),
                    fontSize = 14.sp,
                    textAlign = TextAlign.End
                )
            }

        }
    }
}

@Composable
fun NumberCircleUI(
    iconColor: Color,
    countNumber: String
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .shadow(
                10.dp, CircleShape, true, colorResource(id = R.color.black)
            )

            .clip(CircleShape)
            .background(iconColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = countNumber,
            fontWeight = FontWeight.Bold,
            fontSize = Dimens.LargeTextSize,
            color = colorResource(id = R.color.blue_grey),
            textAlign = TextAlign.Center
        )
    }
}
