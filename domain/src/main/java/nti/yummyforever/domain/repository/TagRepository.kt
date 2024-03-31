package nti.yummyforever.domain.repository

import nti.yummyforever.domain.entities.Product
import nti.yummyforever.domain.entities.Tag

interface TagRepository {
    suspend fun getTagsList() : MutableList<Tag>
}