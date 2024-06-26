package com.tech.quiz_app_mvvm.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.quiz.component.noRippleClickable
import com.tech.quiz_app_mvvm.utils.Dimens

@Composable
fun ButtonBox(
    modifier : Modifier = Modifier,
    text: String,
    padding: Dp = Dimens.SmallPadding,
    borderColor : Color = colorResource(id = R.color.mid_night_blue),
    containerColor : Color = colorResource(id = R.color.blue_grey),
    textColor : Color = colorResource(id = R.color.black),
    fontSize : TextUnit = Dimens.MediumTextSize,
    fraction : Float = 1f,
    onButtonClick :()-> Unit
) {
    Box(
        modifier = modifier
            .padding(padding)
            .border(
                Dimens.SmallBorderWidth,
                borderColor,
                RoundedCornerShape(Dimens.LargeCornerRadius)
            )
            .fillMaxWidth(fraction)
            .height(Dimens.MediumBoxHeight)
            .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
            .background(containerColor)
            .noRippleClickable { onButtonClick() },
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            fontSize = fontSize,
            color = textColor,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

@Preview
@Composable
fun ButtonBoxPreview() {
    ButtonBox(text = "Next") {

    }
}