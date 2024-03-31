package nti.yummyforever.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import nti.yummyforever.data.repository.CategoryRepositoryImpl
import nti.yummyforever.domain.entities.Category
import nti.yummyforever.domain.repository.CategoryRepository
import nti.yummyforever.domain.usecase.CategoryUseCase
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object CategoryModule {
    @Provides
    fun provideCategoryUseCase():CategoryRepositoryImpl{
       return CategoryRepositoryImpl()
    }
    @Provides
    fun provideCategoryUC():CategoryUseCase{
        return CategoryUseCase(provideCategoryUseCase())
    }

}