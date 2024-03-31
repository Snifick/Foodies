package nti.yummyforever.foodies.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.yummyforever.domain.entities.Product
import nti.yummyforever.foodies.R
import nti.yummyforever.foodies.ui.common.button.SelectCount
import nti.yummyforever.foodies.ui.screens.homepage.HomePageViewModel
import nti.yummyforever.foodies.ui.theme.darkColor
import nti.yummyforever.foodies.ui.theme.primaryColor
import nti.yummyforever.foodies.ui.theme.whiteColor

@Composable
fun BasketCard(product: Product,viewModel: HomePageViewModel){

    val thisProductsInBasket = viewModel.selectedItems.collectAsState()

    Row(horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.png1), contentDescription = "",
            modifier = Modifier.size(140.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(142.dp)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = product.name,
                fontSize = 18.sp, color = darkColor,
                fontWeight = FontWeight.Normal
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                SelectCount(viewModel,product,thisProductsInBasket.value.count { it == product })
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = product.price_current.toString() + " ₽",
                        color = darkColor,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (product.price_old != null) {
                        Text(
                            text = product.price_old.toString() + " ₽",
                            color = Color.Gray,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }
            }
        }
    }
}