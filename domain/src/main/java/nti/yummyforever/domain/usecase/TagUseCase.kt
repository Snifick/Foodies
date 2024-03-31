package nti.yummyforever.domain.usecase

import nti.yummyforever.domain.repository.ProductRepository
import nti.yummyforever.domain.repository.TagRepository
import javax.inject.Inject

class TagUseCase @Inject constructor(val tagRepositoryImpl: TagRepository) {
}