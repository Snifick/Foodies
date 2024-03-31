package nti.yummyforever.foodies.ui

import android.content.res.Resources
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import nti.yummyforever.foodies.ui.common.SPLASH_SCREEN
import nti.yummyforever.foodies.ui.entities.MenuState
import nti.yummyforever.foodies.ui.screens.homepage.HomePageViewModel
import nti.yummyforever.foodies.ui.theme.whiteColor

@Composable
fun Foodies() {
    val appState = rememberAppState()
    val menuState = rememberMenuState(appState)
    Scaffold(contentColor = whiteColor) { innerPaddingModifier ->
        NavHost(
            navController = appState.navController,
            startDestination = SPLASH_SCREEN,
            modifier = Modifier
                .padding(innerPaddingModifier)
                .navigationBarsPadding()
        ) {
            foodiesNavigationGraph(menuState)
        }
    }
}

@Composable
fun rememberMenuState(appState: FoodiesAppState, viewModel:HomePageViewModel= hiltViewModel()) = remember(appState) {
    MenuState(appState,viewModel)
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
    FoodiesAppState(navController)
}
