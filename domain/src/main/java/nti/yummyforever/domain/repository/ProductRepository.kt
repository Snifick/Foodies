package nti.yummyforever.domain.repository

import nti.yummyforever.domain.entities.Category
import nti.yummyforever.domain.entities.Product

interface ProductRepository {
    suspend fun getProductsList() : MutableList<Product>
}