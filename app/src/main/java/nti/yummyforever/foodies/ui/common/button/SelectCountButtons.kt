package nti.yummyforever.foodies.ui.common.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.yummyforever.domain.entities.Product
import nti.yummyforever.foodies.R
import nti.yummyforever.foodies.ui.screens.homepage.HomePageViewModel
import nti.yummyforever.foodies.ui.theme.darkColor
import nti.yummyforever.foodies.ui.theme.primaryColor

@Composable
fun SelectCount(viewModel:HomePageViewModel,
                product:Product,
                count:Int){
    Row(
        modifier = Modifier
            .size(124.dp, 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { viewModel.dropProductFromBasket(product) },
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = primaryColor,
                containerColor = Color(0xFFF4F4F4)
            ),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.aspectRatio(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.minus),
                contentDescription = "",

                )
        }
        Text(
            text = "$count",
            fontSize = 20.sp, color = darkColor,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = { viewModel.addProductToBasket(product)},
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = primaryColor,
                containerColor = Color(0xFFF4F4F4)
            ),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.aspectRatio(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = ""
            )

        }


    }
}