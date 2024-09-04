package com.tech.quiz_app_mvvm.presentation.home

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tech.quiz_app_mvvm.R
import com.tech.quiz_app_mvvm.presentation.common.AppDropDownMenu
import com.tech.quiz_app_mvvm.presentation.common.ButtonBox
import com.tech.quiz_app_mvvm.presentation.home.component.DividerComponent
import com.tech.quiz_app_mvvm.presentation.home.component.IconButtonComponent
import com.tech.quiz_app_mvvm.presentation.nav_graph.Routes
import com.tech.quiz_app_mvvm.presentation.score.ScoreDetailScreen
import com.tech.quiz_app_mvvm.utils.Constants
import com.tech.quiz_app_mvvm.utils.Dimens
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    state: StateHomeScreen,
    event: (EventHomeScreen) -> Unit,
    navController: NavController
) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    BackHandler(enabled = drawerState.isOpen) {
        scope.launch {
            drawerState.close()
        }
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = colorResource(id = R.color.dark_slate_blue),
                drawerContentColor = colorResource(id = R.color.white)
            ) {
                DrawerContent(
                    onCloseDrawer = {
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    onPrivacyClick = {
                                     navController.navigate(Routes.PrivacyScreen.route)
                    },
                    onContentClick = {
                        navController.navigate(Routes.ScoreDetailScreen.route)
                    }
                )
            }
        }

    ) {
        Column(
            modifier = Modifier
                .background(color = colorResource(id = R.color.mid_night_blue))
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            HomeHeader {
                scope.launch { drawerState.open() }
            }
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
}

@Composable
fun HomeHeader(
    onMenuClick: () -> Unit
) {
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
                    .clickable {
                        onMenuClick()
                    }
                    .size(Dimens.MediumIconSize),
                tint = colorResource(
                    id = R.color.blue_grey
                )
            )
            Text(
                text = stringResource(id = R.string.app_name),
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

@Composable
fun DrawerContent(
    onCloseDrawer: () -> Unit,
    onPrivacyClick : ()->Unit,
    onContentClick:()->Unit
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.dark_slate_blue))
    ) {

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(Dimens.SmallPadding)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "drawerBack",
                modifier = Modifier
                    .align(
                        Alignment.Start
                    )
                    .clickable { onCloseDrawer() }
            )
            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.quiz),
                    contentDescription = "quiz",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = colorResource(id = R.color.blue_grey),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    fontSize = Dimens.MediumTextSize
                )

            }

            DividerComponent()

            IconButtonComponent(icon = R.drawable.score_meter, iconName = "Your Score") {
                onContentClick()
            }

            DividerComponent()

            IconButtonComponent(icon = R.drawable.privacy_policy, iconName = "Privacy") {
                onCloseDrawer()
                onPrivacyClick()
            }
            DividerComponent()

            IconButtonComponent(icon = R.drawable.rating, iconName = "Rate Us") {
                onCloseDrawer()
            }
            DividerComponent()

            IconButtonComponent(icon = R.drawable.share, iconName = "Share") {
                onCloseDrawer()
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, context.getString(R.string.quiz_description))
                    type = "text/plain"
                }
                // Create a chooser to let the user select the app to share with
                val shareIntent = Intent.createChooser(intent, null)
                context.startActivity(shareIntent)
            }
            DividerComponent()

            Text(
                text = "Follow us",
                color = colorResource(id = R.color.blue_grey),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                fontSize = Dimens.SmallTextSize
            )
            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButtonComponent(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    icon = R.drawable.instagram
                ) {
                    onCloseDrawer()

                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/coder.aman")
                    ).apply {
                        // Optionally, add additional flags
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    context.startActivity(intent)
                }
                Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))

                IconButtonComponent(
                    modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Center,
                    icon = R.drawable.linkedin
                ) {
                    onCloseDrawer()
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/aman-kumar-4aaa631b5/")
                    ).apply {
                        // Optionally, add additional flags
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    context.startActivity(intent)
                }
                Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))

                IconButtonComponent(
                    modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Center,
                    icon = R.drawable.facebook
                ) {
                    onCloseDrawer()
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/coder.amankumargupta")
                    ).apply {
                        // Optionally, add additional flags
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    context.startActivity(intent)
                }
                Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))
            }
            Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "App Version ${
                        context.packageManager.getPackageInfo(
                            context.packageName,
                            0
                        ).versionName ?: "Unknown"
                    }",
                    color = colorResource(id = R.color.blue_grey),
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center,
                    fontSize = Dimens.SmallTextSize
                )
            }

        }
    }
}


//@Preview
//@Composable
//fun PreviewHome() {
//    HomeScreen(
//        state = StateHomeScreen(),
//        event = {},
//        navController = NavController(LocalContext.current)
//    )
//}