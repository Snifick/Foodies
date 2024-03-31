package nti.yummyforever.foodies.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.yummyforever.foodies.R
import nti.yummyforever.foodies.ui.common.button.FoodIconButton
import nti.yummyforever.foodies.ui.theme.darkColor

@Composable
fun TopBar(modifier: Modifier, goBack:()->Unit){
    Row(modifier = Modifier.then(modifier)
        .fillMaxWidth()
        .height(60.dp)
        .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        FoodIconButton(R.drawable.arrow_back) {
            goBack.invoke()
        }
        Text(text = "Корзина", color = darkColor, fontSize = 18.sp,
            modifier = Modifier,
            fontWeight = FontWeight.Bold)
    }
}
