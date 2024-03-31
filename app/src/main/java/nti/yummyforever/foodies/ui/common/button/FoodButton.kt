package nti.yummyforever.foodies.ui.common.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.yummyforever.foodies.ui.theme.primaryColor
import nti.yummyforever.foodies.ui.theme.whiteColor

@Composable
fun FoodButton(label: String, modifier: Modifier, icon: Int? = null, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryColor,
            contentColor = whiteColor
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
    ) {
        if (icon != null) {
            Image(painter = painterResource(id = icon), contentDescription = "")
        }
        Text(text = label, fontSize = 16.sp, color = whiteColor)
    }
}
