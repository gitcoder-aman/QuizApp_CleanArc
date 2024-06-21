package com.tech.quiz_app_mvvm.quiz.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tech.quiz_app_mvvm.utils.Dimens

@Composable
fun ShimmerEffectBooleanQuiz() {

    Column {
        Row(
            modifier = Modifier.padding(Dimens.SmallPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(40.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .weight(1f)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(Dimens.MediumSpacerHeight))
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(40.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .weight(1f)
                    .shimmerEffect()
            )
        }

        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.VerySmallPadding)
                .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

        Row(
            modifier = Modifier.padding(Dimens.SmallPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(40.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .weight(1f)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(40.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .weight(9f)
                    .shimmerEffect()
            )
        }
        Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))
        Column(
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.MediumBoxHeight)
                    .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.MediumBoxHeight)
                    .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.height(Dimens.ExtraLargeSpacerHeight))

            Row {
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth()
                        .height(Dimens.MediumBoxHeight)
                        .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))

                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth()
                        .height(Dimens.MediumBoxHeight)
                        .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
                        .shimmerEffect()
                )
            }
            Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

        }
    }
}


@Preview
@Composable
fun ShimmerEffectBooleanQuizPreview() {
    ShimmerEffectBooleanQuiz()
}