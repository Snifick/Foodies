package nti.yummyforever.foodies.ui.entities

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import nti.yummyforever.domain.entities.Product
import nti.yummyforever.foodies.ui.FoodiesAppState
import nti.yummyforever.foodies.ui.common.BASKET_SCREEN
import nti.yummyforever.foodies.ui.common.DESCRIPTION_SCREEN
import nti.yummyforever.foodies.ui.common.HOME_SCREEN
import nti.yummyforever.foodies.ui.screens.homepage.HomePageViewModel

@Stable
class MenuState(val appState: FoodiesAppState,val viewModel: HomePageViewModel) {
    fun goHomeAfterSplash(){
        appState.clearAndNavigate(HOME_SCREEN)
    }
    fun openBasketScreen(){
        appState.navigate(BASKET_SCREEN)
    }
    fun openProductDescription(product:Product){
        viewModel.descriptionForProduct.value = product
        appState.navigate(DESCRIPTION_SCREEN)
    }
    fun goBack() {
        appState.popUp()
    }

}