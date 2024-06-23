package com.tech.quiz_app_mvvm.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.quiz.component.noRippleClickable
import com.tech.quiz_app_mvvm.utils.Dimens

@Composable
fun IconButtonComponent(
    icon: Int,
    iconName: String = "",
    modifier: Modifier = Modifier,
    horizontalArrangement : Arrangement.Horizontal = Arrangement.Start,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .noRippleClickable { onClick() }
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = iconName,
            modifier = Modifier.size(45.dp),
            tint = colorResource(id = R.color.blue_grey)
        )
        Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))
        Text(
            text = iconName,
            color = colorResource(id = R.color.blue_grey),
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
            fontSize = Dimens.MediumTextSize
        )
    }
}
