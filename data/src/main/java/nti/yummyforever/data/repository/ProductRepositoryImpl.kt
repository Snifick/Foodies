package nti.yummyforever.data.repository

import android.util.Log
import nti.yummyforever.data.network.RetrofitClient
import nti.yummyforever.domain.entities.Category
import nti.yummyforever.domain.entities.Product
import nti.yummyforever.domain.repository.ProductRepository

class ProductRepositoryImpl : ProductRepository {
    override suspend fun getProductsList(): MutableList<Product> {
        try {
            return RetrofitClient.apiService.getProducts() as MutableList<Product>
        } catch (e: Exception) {

            return mutableListOf()
        }
    }
}