package nti.yummyforever.foodies.ui.common.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.yummyforever.foodies.ui.theme.darkColor
import nti.yummyforever.foodies.ui.theme.primaryColor
import nti.yummyforever.foodies.ui.theme.whiteColor

@Composable
fun CategoryButton(label:String, isSelected: Boolean, onClick:()->Unit){
    Button(onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = if(isSelected) primaryColor else whiteColor,
            contentColor = if(isSelected) whiteColor else darkColor
        ),
        shape = RoundedCornerShape(8.dp)) {
        Text(text = label, fontSize = 14.sp)
    }
}