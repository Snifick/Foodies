package nti.yummyforever.foodies.ui.screens.homepage

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nti.yummyforever.domain.entities.Category
import nti.yummyforever.domain.entities.Product
import nti.yummyforever.domain.entities.Tag
import nti.yummyforever.domain.usecase.CategoryUseCase
import nti.yummyforever.domain.usecase.ProductUseCase
import nti.yummyforever.domain.usecase.TagUseCase
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor (
    private val categoryUseCase:CategoryUseCase,
    private val productUseCase:ProductUseCase,
    private val tagUseCase: TagUseCase
    ) : ViewModel() {
    val cost = mutableStateOf(0)
    val showFilters = mutableStateOf(false)
    val searchFood = mutableStateOf(false)
    val searchValue = mutableStateOf("")
    val tagsList = mutableStateOf(mutableListOf<Tag>())
    val categoriesItems = mutableStateOf(listOf<Category>())
    val productItems = mutableStateOf(listOf<Product>())
    val filteredProductItems = mutableStateOf(listOf<Product>())
    val filteredProductItemsByName = mutableStateOf(listOf<Product>())
    val selectedItems = MutableStateFlow(listOf<Product>())

    //filters
    val withOutMeat = mutableStateOf(false)
    val spicy = mutableStateOf(false)
    val withDiscount = mutableStateOf(false)
    val badNetwork = mutableStateOf(false)
    val descriptionForProduct = mutableStateOf<Product?>(null)

    init {
      viewModelScope.launch {
          withContext(Dispatchers.IO){
              categoriesItems.value =  categoryUseCase.categoryRepositoryImpl.getCategoriesList()
              productItems.value = productUseCase.productRepositoryImpl.getProductsList()
              tagsList.value = tagUseCase.tagRepositoryImpl.getTagsList()
              if(categoriesItems.value.isNotEmpty())
              filterItems(categoriesItems.value.first().id)
              else
                  badNetwork.value = true

          }
      }
    }
    fun addProductToBasket(product: Product){
       selectedItems.value += product
        cost.value = 0
        selectedItems.value.forEach { cost.value += it.price_current }
    }

    fun dropProductFromBasket(product: Product){
        selectedItems.value -= product
        cost.value = 0
        selectedItems.value.forEach { cost.value += it.price_current }
    }

    fun filterItems(id: Int) {
        reFilter()
        filteredProductItems.value = productItems.value.filter { it.category_id == id }
        cost.value = 0
        selectedItems.value.forEach { cost.value += it.price_current }
    }
    fun reFilter(){
        filteredProductItems.value = productItems.value
        if(withOutMeat.value){
            filteredProductItems.value  = filteredProductItems.value.filter {
                it.tag_ids.contains(2)
            }
        }
        if(spicy.value){
            filteredProductItems.value  = filteredProductItems.value.filter {
                it.tag_ids.contains(4)
            }
        }
        if(spicy.value){
            filteredProductItems.value  = filteredProductItems.value.filter {
                it.price_old!=null && it.price_current < it.price_old!!
            }
        }

    }
    fun filterByName(sequence: String){
        filteredProductItemsByName.value = filteredProductItems.value.filter { it.name.contains(sequence) }
    }


}