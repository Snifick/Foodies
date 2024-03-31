package nti.yummyforever.domain.repository

import nti.yummyforever.domain.entities.Category

interface CategoryRepository {
    suspend fun getCategoriesList() : MutableList<Category>
}