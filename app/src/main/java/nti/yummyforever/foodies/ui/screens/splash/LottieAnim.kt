package nti.yummyforever.foodies.ui.screens.splash

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import nti.yummyforever.foodies.R

@Composable
fun LottieAnim(){
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.splash_screen_animation)
    )
   LottieAnimation(composition =composition,
       modifier = Modifier,
       iterations = 1)
}