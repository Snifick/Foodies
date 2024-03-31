package nti.yummyforever.foodies.ui.screens.homepage.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.yummyforever.foodies.R
import nti.yummyforever.foodies.ui.common.button.FoodIconButton
import nti.yummyforever.foodies.ui.screens.homepage.HomePageViewModel
import nti.yummyforever.foodies.ui.theme.darkColor
import nti.yummyforever.foodies.ui.theme.grayColor
import nti.yummyforever.foodies.ui.theme.whiteColor

@Composable
fun TopComponent(viewModel: HomePageViewModel){
    val selectedFilters = remember{ mutableStateOf(0) }

    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(viewModel.withOutMeat.value,viewModel.spicy.value,viewModel.withDiscount.value){
        selectedFilters.value = 0
        if(viewModel.withOutMeat.value)
            selectedFilters.value++
        if(viewModel.spicy.value)
            selectedFilters.value++
        if(viewModel.withDiscount.value)
            selectedFilters.value++
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(54.dp)
        .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        if(!viewModel.searchFood.value){
            FoodIconButton(R.drawable.filters,Modifier, selectedFilters.value) { viewModel.showFilters.value = true }
            Image(painter = painterResource(id = R.drawable.foodies_big), contentDescription = "logo" )
            FoodIconButton(R.drawable.search) {
                viewModel.searchFood.value = true
            }
        }
        else{
            FoodIconButton(R.drawable.arrow_back) { viewModel.searchFood.value = !viewModel.searchFood.value }

            TextField(value = viewModel.searchValue.value , onValueChange ={
                viewModel.searchValue.value = it
                viewModel.filterByName(viewModel.searchValue.value)
                                                                 },
                modifier = Modifier.fillMaxWidth(0.9f).focusRequester(focusRequester),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = whiteColor,
                    disabledContainerColor = whiteColor,
                    focusedContainerColor = whiteColor,
                    focusedTextColor = darkColor,
                    unfocusedTextColor = darkColor,
                    unfocusedPlaceholderColor = grayColor,
                    focusedPlaceholderColor = grayColor,
                    disabledPlaceholderColor = grayColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = { Text(text =  "Найти блюдо",
                    color = grayColor, fontSize = 16.sp)
                },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )
            LaunchedEffect(key1 = Unit , block ={
                focusRequester.requestFocus()
            } )
            FoodIconButton(R.drawable.cancel) {
                viewModel.searchFood.value = false
            }
        }

    }
}