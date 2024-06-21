package com.tech.quiz_app_mvvm.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.presentation.common.AppDropDownMenu
import com.tech.quiz_app_mvvm.presentation.common.ButtonBox
import com.tech.quiz_app_mvvm.presentation.nav_graph.Routes
import com.tech.quiz_app_mvvm.utils.Constants
import com.tech.quiz_app_mvvm.utils.Dimens

@Composable
fun HomeScreen(
    state: StateHomeScreen,
    event: (EventHomeScreen) -> Unit,
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeHeader()

        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
        AppDropDownMenu(
            menuName = "Number of Questions:",
            menuList = Constants.numbersAsString,
            text = state.numberOfQuiz.toString(),
            onDropDownClick = {
                event(EventHomeScreen.SetNumberOfQuizzes(it.toInt()))
            })

        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Category:",
            menuList = Constants.category,
            text = state.category,
            onDropDownClick = {
                event(EventHomeScreen.SetQuizCategory(it))
            })

        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Difficulty",
            menuList = Constants.difficulty,
            text = state.difficulty,
            onDropDownClick = {
                event(EventHomeScreen.SetQuizDifficulty(it))
            })

        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Type:",
            menuList = Constants.type,
            text = state.type,
            onDropDownClick = {
                event(EventHomeScreen.SetQuizType(it))
            })

        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

        ButtonBox(text = "Generate Quiz", padding = Dimens.MediumPadding) {
            navController.navigate(
                route = Routes.QuizScreen.passQuizParams(
                    numOfQuizzes = state.numberOfQuiz,
                    category = state.category,
                    difficulty = state.difficulty,
                    type = state.type
                )
            )
        }

    }
}

@Composable
fun HomeHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.HomeTopBoxHeight)
            .background(
                color = colorResource(id = R.color.dark_slate_blue),
                shape = RoundedCornerShape(
                    bottomStart = Dimens.ExtraLargeCornerRadius,
                    bottomEnd = Dimens.ExtraLargeCornerRadius
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.LargePadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.google_menu),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(Dimens.MediumIconSize),
                tint = colorResource(
                    id = R.color.blue_grey
                )
            )
            Text(
                text = "Quiz App",
                color = colorResource(id = R.color.blue_grey),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(3.5f),
                textAlign = TextAlign.Center,
                fontSize = Dimens.LargeTextSize
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_account_box_24),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(Dimens.MediumIconSize),
                tint = colorResource(
                    id = R.color.blue_grey
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeScreen(
        state = StateHomeScreen(),
        event = {},
        navController = NavController(LocalContext.current)
    )
}