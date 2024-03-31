package nti.yummyforever.foodies.ui.screens.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.yummyforever.domain.entities.Product
import nti.yummyforever.foodies.R
import nti.yummyforever.foodies.ui.theme.darkColor
import nti.yummyforever.foodies.ui.theme.grayColor
import nti.yummyforever.foodies.ui.theme.primaryColor
import nti.yummyforever.foodies.ui.theme.whiteColor

@Stable
@Composable
fun ProductCard(product: Product,modifier:Modifier,count:Int,viewModel: HomePageViewModel,
                goToProductDescription:(product:Product)->Unit){

    Box(modifier = Modifier){
        Column(verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .background(Color(0xFFF4F4F4), RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    goToProductDescription.invoke(product)
                }){
            Image(painter = painterResource(id = R.drawable.png1), contentDescription = "tom yan",
                modifier = Modifier
                    .align(Alignment.End)
                    .size(190.dp),
                contentScale = ContentScale.Fit
            )
            Text(text = product.name, color = darkColor, fontSize = 15.sp,
                modifier = Modifier.padding(horizontal = 8.dp),
                maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = "${product.measure} ${product.measure_unit}",
                color = grayColor, fontSize = 14.sp,
                modifier = Modifier.padding(horizontal =  8.dp))

            if(count==0) {
                Button(
                    onClick = { viewModel.addProductToBasket(product) },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = whiteColor,
                        contentColor = darkColor
                    ),
                    elevation = ButtonDefaults.buttonElevation(0.dp),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 8.dp)
                        .size(154.dp, 40.dp),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                    ) {
                        Text(
                            text = product.price_current.toString() + " ₽",
                            color = darkColor,
                            fontSize = 15.sp,
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
            else{
                Row(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp)
                    .size(154.dp, 40.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = { viewModel.dropProductFromBasket(product)},
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(contentColor = primaryColor,
                            containerColor = whiteColor),
                        contentPadding = PaddingValues(0.dp)) {
                        Image(painter = painterResource(id = R.drawable.minus), contentDescription = "")
                    }
                    Text(text = "$count",
                        fontSize = 18.sp, color =  darkColor)
                    Button(onClick = {  viewModel.addProductToBasket(product)},
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(contentColor = primaryColor,
                            containerColor = whiteColor),
                        contentPadding = PaddingValues(0.dp)) {
                        Image(painter = painterResource(id = R.drawable.plus), contentDescription = "")

                    }
                }

            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)){
            val miniPngs = remember{
                listOf(R.drawable.discount,
                    R.drawable.vegan,
                    R.drawable.spicy,
                    R.drawable.spicy,
                    R.drawable.discount)
            }
                viewModel.tagsList.value.forEach { tag ->
                    if(product.tag_ids.contains(tag.id)){
                        Image(painter = painterResource(id = miniPngs[tag.id-1]), contentDescription = "",
                            modifier = Modifier.size(28.dp))
                    }
                }
        }
    }


}
