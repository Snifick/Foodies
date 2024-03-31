package nti.yummyforever.foodies.ui

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import nti.yummyforever.foodies.ui.common.BASKET_SCREEN
import nti.yummyforever.foodies.ui.common.DESCRIPTION_SCREEN
import nti.yummyforever.foodies.ui.common.HOME_SCREEN
import nti.yummyforever.foodies.ui.common.SPLASH_SCREEN
import nti.yummyforever.foodies.ui.entities.MenuState
import nti.yummyforever.foodies.ui.screens.basket.BasketPage
import nti.yummyforever.foodies.ui.screens.description.DescriptionPage
import nti.yummyforever.foodies.ui.screens.homepage.HomePage
import nti.yummyforever.foodies.ui.screens.splash.SplashScreen

fun NavGraphBuilder.foodiesNavigationGraph(menuState: MenuState) {
    composable(SPLASH_SCREEN) {
        SplashScreen(openHomeScreen = menuState::goHomeAfterSplash)
    }
    composable(HOME_SCREEN) {
        HomePage(menuState.viewModel, openProductScreen = menuState::openProductDescription,openBasketScreen = menuState::openBasketScreen)
    }
    composable(BASKET_SCREEN){
        BasketPage(menuState.viewModel,  goBack = menuState::goBack)
    }
    composable(DESCRIPTION_SCREEN){
        val selectedProducts = menuState.viewModel.selectedItems.collectAsState()
        if(menuState.viewModel.descriptionForProduct.value!=null) {
            DescriptionPage(menuState.viewModel,
                selectedProducts.value,
                menuState.viewModel.descriptionForProduct.value!!,
                { menuState.goBack() },
                { product -> menuState.viewModel.addProductToBasket(product) })
        }
    }

}