package nti.yummyforever.foodies.ui.screens.basket

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import nti.yummyforever.foodies.R
import nti.yummyforever.foodies.ui.common.button.FoodButton
import nti.yummyforever.foodies.ui.screens.homepage.HomePageViewModel
import nti.yummyforever.foodies.ui.theme.grayColor
import nti.yummyforever.foodies.ui.theme.whiteColor

@Composable
fun BasketPage( viewModel:HomePageViewModel, goBack:()->Unit){
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current
    val selectedProducts = viewModel.selectedItems.collectAsState()
    SideEffect { systemUiController.setSystemBarsColor(whiteColor) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier
            .fillMaxSize()){
            TopBar( modifier = Modifier, goBack)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                if(selectedProducts.value.isNotEmpty()){
                    LazyColumn(modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 60.dp)
                    ){
                        items(viewModel.selectedItems.value.toSet().toList()){ product->
                            BasketCard(product,viewModel)
                            Divider(modifier = Modifier.fillMaxWidth(),color = Color(0xFFE7E7E7),
                                thickness = 1.dp)
                        }
                    }
                }
                else{
                    Text(text = "Пусто, выберите блюда\nв каталоге :)", color = grayColor, fontSize = 16.sp,
                        modifier = Modifier,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center)
                }
            }



        }

        if(selectedProducts.value.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(80.dp)
                    .shadow(4.dp)
                    .background(whiteColor), contentAlignment = Alignment.Center
            ) {
                FoodButton(
                    label = "Заказать за ${viewModel.cost.value} ₽",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)

                        .height(56.dp)
                ) {
                    Toast.makeText(context, "Заказ оформлен!", Toast.LENGTH_LONG).show()
                    viewModel.selectedItems.value = listOf()


                }
            }
        }

    }
}

