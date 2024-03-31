package nti.yummyforever.foodies.ui.common.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.yummyforever.foodies.R
import nti.yummyforever.foodies.ui.theme.primaryColor
import nti.yummyforever.foodies.ui.theme.whiteColor

@Composable
fun FoodIconButton(id:Int,modifier: Modifier=Modifier, count:Int?=null, onClick:() -> Unit){

    Box(modifier = Modifier.then(modifier)
        .size(42.dp)
        .clip(
            CircleShape
        )
        .clickable(
            indication = rememberRipple(color = primaryColor),
            interactionSource = remember { MutableInteractionSource() },
            onClick = onClick
        ),
        contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = id), contentDescription = "",
            modifier = Modifier
                .size(24.dp)
        )
        if(count!=null && count!=0){
            Text(text = "$count", fontSize = 12.sp, color = whiteColor,
                modifier = Modifier.offset(8.dp,-8.dp).size(18.dp).background(primaryColor, CircleShape).aspectRatio(1f),
                textAlign = TextAlign.Center)

        }
      }
}