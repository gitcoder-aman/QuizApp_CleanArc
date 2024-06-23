package com.tech.quiz_app_mvvm.presentation.home.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.utils.Dimens

@Composable
fun DividerComponent() {
    Divider(
        thickness = 1.dp,
        color = colorResource(id = R.color.mid_night_blue),
        modifier = Modifier.padding(
            top = Dimens.SmallSpacerHeight,
            bottom = Dimens.SmallSpacerHeight
        )
    )
}