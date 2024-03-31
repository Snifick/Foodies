package nti.yummyforever.data.repository

import nti.yummyforever.data.network.RetrofitClient
import nti.yummyforever.domain.entities.Product
import nti.yummyforever.domain.entities.Tag
import nti.yummyforever.domain.repository.TagRepository

class TagRepositoryImpl  : TagRepository {
    override suspend fun getTagsList(): MutableList<Tag> {
        try {
            return RetrofitClient.apiService.getTags() as MutableList<Tag>
        } catch (e: Exception) {

            return mutableListOf()
        }
    }
}
