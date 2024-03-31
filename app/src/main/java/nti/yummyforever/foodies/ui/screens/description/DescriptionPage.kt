package nti.yummyforever.foodies.ui.screens.description

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import nti.yummyforever.domain.entities.Product
import nti.yummyforever.foodies.R
import nti.yummyforever.foodies.ui.common.button.FoodButton
import nti.yummyforever.foodies.ui.common.button.FoodIconButton
import nti.yummyforever.foodies.ui.common.button.SelectCount
import nti.yummyforever.foodies.ui.screens.homepage.HomePageViewModel
import nti.yummyforever.foodies.ui.theme.darkColor
import nti.yummyforever.foodies.ui.theme.grayColor
import nti.yummyforever.foodies.ui.theme.primaryColor
import nti.yummyforever.foodies.ui.theme.whiteColor

@Composable
fun DescriptionPage(viewModel:HomePageViewModel, selectedItems:List<Product>, product: Product,goBack:()->Unit,addToBasket:(product:Product)->Unit){
    val systemUiController = rememberSystemUiController()
    SideEffect { systemUiController.setSystemBarsColor(whiteColor) }
    Box(modifier = Modifier.fillMaxSize()){

        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Image(
                painter = painterResource(id = R.drawable.bigimg), contentDescription = "",
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = product.name, color = darkColor, fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = product.description, color = grayColor, fontSize = 15.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LineDescription("Вес", "${product.measure} ${product.measure_unit}")
            LineDescription("Энерг. ценность", "${product.energy_per_100_grams} ккал")
            LineDescription("Белки", "${product.proteins_per_100_grams} ${product.measure_unit}")
            LineDescription("Жиры", "${product.fats_per_100_grams} ${product.measure_unit}")
            LineDescription(
                "Углеводы",
                "${product.carbohydrates_per_100_grams} ${product.measure_unit}"
            )
            Divider(modifier = Modifier.fillMaxWidth(), 1.dp, Color(0xFFD8D8D8))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(whiteColor), contentAlignment = Alignment.Center
            ) {
                FoodButton(
                    label = if (!selectedItems.contains(product)) " В корзину за ${product.price_current} ₽" else " Уже в корзине!",
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .fillMaxSize(),
                    icon = R.drawable.basket
                ) {
                    addToBasket.invoke(product)
                }
            }
            if (selectedItems.contains(product)) {
                SelectCount(viewModel, product,selectedItems.count { it == product })
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
        FoodIconButton(id = R.drawable.back_dark,modifier = Modifier
            .align(Alignment.TopStart)
            .padding(16.dp)) {
            goBack.invoke()
        }
    }
}
@Composable
fun LineDescription(text:String, value:String){
    Divider(modifier = Modifier.fillMaxWidth(),1.dp,Color(0xFFD8D8D8))
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Text(text = text, color = grayColor, fontSize = 14.sp)
        Text(text = value, color = darkColor, fontSize = 16.sp)
    }

}