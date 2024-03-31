package nti.yummyforever.foodies.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import nti.yummyforever.foodies.ui.theme.primaryColor

@Composable
fun SplashScreen(openHomeScreen: ()-> Unit){
    val systemUiController = rememberSystemUiController()
    SideEffect { systemUiController.setSystemBarsColor(primaryColor) }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF05412)),
        contentAlignment = Alignment.Center){
        LottieAnim()
    }
    LaunchedEffect(key1 = Unit, block = {
        delay(1750)
        openHomeScreen.invoke()
    })

}

