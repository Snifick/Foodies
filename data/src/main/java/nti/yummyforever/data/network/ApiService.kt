package nti.yummyforever.data.network

import nti.yummyforever.domain.entities.Category
import nti.yummyforever.domain.entities.Product
import nti.yummyforever.domain.entities.Tag
import retrofit2.http.GET

interface ApiService {
    @GET("Categories.json")
    suspend fun getCategories(): List<Category>

    @GET("Products.json")
    suspend fun getProducts():List<Product>
    @GET("Tags.json")
    suspend fun getTags():List<Tag>
}