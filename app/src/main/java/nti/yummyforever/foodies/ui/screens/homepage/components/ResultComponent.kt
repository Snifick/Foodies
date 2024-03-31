package nti.yummyforever.foodies.ui.screens.homepage.components



import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.yummyforever.domain.entities.Product
import nti.yummyforever.foodies.R
import nti.yummyforever.foodies.ui.common.button.CategoryButton
import nti.yummyforever.foodies.ui.common.button.FoodButton
import nti.yummyforever.foodies.ui.screens.homepage.HomePageViewModel
import nti.yummyforever.foodies.ui.screens.homepage.ProductCard
import nti.yummyforever.foodies.ui.theme.darkColor
import nti.yummyforever.foodies.ui.theme.grayColor
import nti.yummyforever.foodies.ui.theme.primaryColor
import nti.yummyforever.foodies.ui.theme.whiteColor
import kotlin.random.Random

@Composable
fun ResultComponent(viewModel: HomePageViewModel,
                    openProductScreen:(product:Product)->Unit,
                    openBasketScreen: () -> Unit) {
    val lazyGridState = rememberLazyGridState()
    val lazyRowState = rememberLazyListState()
    val selectedCategory = remember { mutableStateOf(0) }
    val productsInBasket = viewModel.selectedItems.collectAsState()
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyRow(
                state = lazyRowState,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                itemsIndexed(viewModel.categoriesItems.value) { index, category ->
                    CategoryButton(
                        label = category.name,
                        isSelected = selectedCategory.value == index
                    ) {
                        selectedCategory.value = index
                        viewModel.filterItems(category.id)
                    }
                }
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ErrorMessage(viewModel)

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize(),
                state = lazyGridState,
                columns = GridCells.Adaptive(170.dp),
                contentPadding = PaddingValues(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 96.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(if(viewModel.searchValue.value.isEmpty())
                    viewModel.filteredProductItems.value
                    else
                        viewModel.filteredProductItemsByName.value, key = { it.id }) { product ->
                    val showProduct = remember { mutableStateOf(false) }
                    LaunchedEffect(key1 = product, block = { showProduct.value = true })
                 androidx.compose.animation.AnimatedVisibility(visible = showProduct.value,
                        enter = fadeIn(tween(350, Random.nextInt(180)))
                                + slideInHorizontally(
                            tween(
                                400,
                                Random.nextInt(180)
                            )
                        ) { -24 }) {
                        ProductCard(
                            product = product,
                            modifier = Modifier,
                            count = productsInBasket.value.count { it == product },
                            viewModel
                        ){
                            product ->
                            openProductScreen.invoke(product)
                        }
                    }

                }

            }
        }


        }
        AnimatedVisibility(
            productsInBasket.value.isNotEmpty(),
            enter = slideInVertically(tween(400)) { 20 } + fadeIn(tween(300)),
            exit = slideOutVertically(tween(300)) { 20 } + fadeOut(tween(200)),
            modifier = Modifier
                .align(Alignment.BottomCenter)

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .shadow(4.dp)
                    .background(whiteColor), contentAlignment = Alignment.Center
            ) {
                FoodButton(
                    label = " ${viewModel.cost.value} ₽", modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .fillMaxSize(), icon = R.drawable.basket
                ) {
                    openBasketScreen.invoke()
                }
            }

        }
    }

}
@Composable
fun ErrorMessage(viewModel:HomePageViewModel){
    if(viewModel.filteredProductItems.value.isEmpty()&&viewModel.productItems.value.isNotEmpty() && !viewModel.badNetwork.value){
        Text(text = "Таких блюд нет :(\nПопробуйте изменить фильтры", color = grayColor, fontSize = 16.sp,
            modifier = Modifier,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center)
    }
    if(viewModel.filteredProductItemsByName.value.isEmpty() && viewModel.searchValue.value.isNotEmpty() && !viewModel.badNetwork.value){
        Text(text = "Таких блюд не нашлось :(", color = grayColor, fontSize = 16.sp,
            modifier = Modifier,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center)
    }
    if(viewModel.productItems.value.isEmpty() && !viewModel.badNetwork.value){
        CircularProgressIndicator(color = primaryColor, modifier = Modifier.size(48.dp))
    }
    if(viewModel.badNetwork.value){
        Text(text = "Плохое интернет-соединение :(\nПопробуйте его восстановить", color = grayColor, fontSize = 16.sp,
            modifier = Modifier,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center)
    }
}