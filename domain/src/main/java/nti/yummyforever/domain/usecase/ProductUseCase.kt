package nti.yummyforever.domain.usecase

import nti.yummyforever.domain.repository.CategoryRepository
import nti.yummyforever.domain.repository.ProductRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(val productRepositoryImpl: ProductRepository) {
}