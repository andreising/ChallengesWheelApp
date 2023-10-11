package com.andreisingeleytsev.challengeswheelapp.ui.navigation


import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andreisingeleytsev.challengeswheelapp.ui.screens.game_screen.GameScreen
import com.andreisingeleytsev.challengeswheelapp.ui.screens.menu_screen.MenuScreen
import com.andreisingeleytsev.challengeswheelapp.ui.utils.Routes


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(navController = navController, startDestination = Routes.MENU_SCREEN) {
        composable(Routes.MENU_SCREEN) {
            MenuScreen(navController)
        }
        composable(Routes.GAME_SCREEN+"/{isQuestions}") {
            GameScreen(
                paddingValues = paddingValues,
                navHostController = navController
            )
        }
    }
}