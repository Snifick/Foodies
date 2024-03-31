package nti.yummyforever.foodies.ui.screens.homepage

import android.app.Activity
import android.view.MotionEvent
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import nti.yummyforever.domain.entities.Product
import nti.yummyforever.foodies.ui.screens.homepage.components.FilterComponent
import nti.yummyforever.foodies.ui.screens.homepage.components.ResultComponent
import nti.yummyforever.foodies.ui.screens.homepage.components.TopComponent
import nti.yummyforever.foodies.ui.theme.whiteColor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomePage(viewModel: HomePageViewModel, openProductScreen:(product: Product)->Unit, openBasketScreen: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val dark = animateColorAsState(
        targetValue = if (viewModel.showFilters.value) { Color(0x66292929) }
        else { Color(0x00FFFFFF) },
        label = ""
    )
    val context = LocalContext.current as Activity
    SideEffect {
        if(!viewModel.showFilters.value) systemUiController.setSystemBarsColor(whiteColor)
        else systemUiController.setSystemBarsColor(dark.value)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        viewModel.showFilters.value = false
                    }
                }
                false
            }) {
            TopComponent(viewModel)
            ResultComponent(viewModel, openProductScreen, openBasketScreen)
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .background(dark.value))
        FilterComponent(viewModel,Modifier.align(Alignment.BottomCenter))
    }
    BackHandler {
        if(viewModel.showFilters.value)
            viewModel.showFilters.value = false
        else
            context.finishAffinity()

    }

}