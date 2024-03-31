package nti.yummyforever.data.repository

import nti.yummyforever.data.network.RetrofitClient
import nti.yummyforever.domain.entities.Category
import nti.yummyforever.domain.repository.CategoryRepository

class CategoryRepositoryImpl() : CategoryRepository {

   override suspend fun getCategoriesList(): MutableList<Category> {
       try {
           return RetrofitClient.apiService.getCategories() as MutableList<Category>
       } catch (e: Exception) {
           return mutableListOf()
       }
   }
}