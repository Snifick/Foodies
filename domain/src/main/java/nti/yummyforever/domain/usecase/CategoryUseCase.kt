package nti.yummyforever.domain.usecase

import nti.yummyforever.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryUseCase @Inject constructor(val categoryRepositoryImpl:CategoryRepository) {
    // тут могла бы быть еще бизнес логика, а не только подяжка данных, и тут бы мы ее прописали
}