package nti.yummyforever.foodies.ui.screens.homepage.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import nti.yummyforever.foodies.ui.common.button.FoodButton
import nti.yummyforever.foodies.ui.screens.homepage.HomePageViewModel
import nti.yummyforever.foodies.ui.theme.darkColor
import nti.yummyforever.foodies.ui.theme.whiteColor

@Composable
fun FilterComponent(viewModel: HomePageViewModel,
                    modifier: Modifier){
    AnimatedVisibility(visible = viewModel.showFilters.value,
        enter = slideInVertically { 40 },
        exit = slideOutVertically { 40 },
        modifier = modifier){
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(whiteColor, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)){
            Text(text = "Подобрать блюда",
                fontSize = 22.sp, color =  darkColor,
                fontWeight = FontWeight.Bold
            )
            SelectComponent(viewModel.withOutMeat,"Без мяса") { viewModel.reFilter() }
            Divider(modifier = Modifier.fillMaxWidth())
            SelectComponent(viewModel.spicy,"Острое") { viewModel.reFilter() }
            Divider(modifier = Modifier.fillMaxWidth())
            SelectComponent(viewModel.withDiscount,"Со скидкой") { viewModel.reFilter() }
            FoodButton(label = "Готово", modifier = Modifier.height(50.dp), icon = null) {
                viewModel.showFilters.value = false
            }
        }
    }
}

@Composable
fun SelectComponent(parameter:MutableState<Boolean>,label:String,reFilter:()->Unit){
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Text(text = label,
                fontSize = 18.sp, color =  darkColor,
                fontWeight = FontWeight.Light
            )
            Checkbox(checked = parameter.value, onCheckedChange = {parameter.value = it
                reFilter()
            },
                modifier = Modifier.size(48.dp))

        }
}
