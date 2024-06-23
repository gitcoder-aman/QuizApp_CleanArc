package com.tech.quiz_app_mvvm.presentation.score.component

import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.ui.theme.LightOrangeColor
import com.tech.quiz_app_mvvm.ui.theme.OrangeColor
import com.tech.quiz_app_mvvm.utils.Dimens

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreInterface(
    countNumber :String = "1",
    category : String = "Science & Computers",
    totalScore : String = "10",
    timeDuration : String = "30s",
    backgroundColor : Color = LightOrangeColor,
    iconColor: Color = OrangeColor,
//    modifier : Modifier = Modifier.padding(Dimens.SmallPadding)
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

            Spacer(modifier = Modifier.width(Dimens.MediumSpacerHeight))

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(Dimens.VerySmallPadding)
            ) {
                Text(
                    text = category,
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    fontSize = Dimens.SmallTextSize
                )
                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
                Text(
                    text = totalScore,
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start,
                    fontSize = Dimens.MediumTextSize
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.clock_icon),
                    contentDescription = "clock_icon",
                    tint = iconColor
                )
                Text(
                    text = timeDuration,
                    color = colorResource(id = R.color.black),
                    modifier = Modifier,
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
