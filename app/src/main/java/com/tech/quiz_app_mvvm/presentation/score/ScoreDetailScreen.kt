package com.tech.quiz_app_mvvm.presentation.score

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.presentation.score.component.ScoreInterface
import com.tech.quiz_app_mvvm.ui.theme.LightSkyColor
import com.tech.quiz_app_mvvm.ui.theme.LightVioletColor
import com.tech.quiz_app_mvvm.ui.theme.SkyColor
import com.tech.quiz_app_mvvm.ui.theme.VioletColor
import com.tech.quiz_app_mvvm.utils.Dimens

@Composable
fun ScoreDetailScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 200.dp)
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.blue_grey), shape = RoundedCornerShape(
                        topStart = Dimens.ExtraLargeCornerRadius,
                        topEnd = Dimens.ExtraLargeCornerRadius
                    )
                )
                .padding(Dimens.SmallPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
            ScoreInterface()
            ScoreInterface(
                countNumber = "2",
                category = "General Knowledge",
                totalScore = "20",
                timeDuration = "1m",
                backgroundColor = LightSkyColor,
                iconColor = SkyColor
            )
            ScoreInterface(
                countNumber = "3",
                category = "English",
                totalScore = "30",
                timeDuration = "30s",
                backgroundColor = LightVioletColor,
                iconColor = VioletColor
            )
        }
    }
}

@Composable
fun ScoreDetailHeader() {

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
                        .clickable { }
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            Text(
                text = "Your Scores",
                color = colorResource(id = R.color.blue_grey),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                fontSize = Dimens.LargeTextSize
            )
        }

        ScoreDetailScreen()
    }
}

@Preview
@Composable
fun ScoreDetailScreenPreview() {
    ScoreDetailHeader()
}