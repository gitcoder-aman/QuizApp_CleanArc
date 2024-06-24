package com.tech.quiz_app_mvvm.quiz.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.utils.Dimens

@Composable
fun ErrorComposableScreen(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.no_data_found))

            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(Dimens.NoDataFoundLottieAnimationSize),
                iterations = 100
            )
            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

            Text(
                text = error, style = TextStyle(
                    color = colorResource(id = R.color.white),
                    fontSize = Dimens.SmallTextSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}