package nti.yummyforever.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import nti.yummyforever.data.repository.ProductRepositoryImpl
import nti.yummyforever.data.repository.TagRepositoryImpl
import nti.yummyforever.domain.usecase.ProductUseCase
import nti.yummyforever.domain.usecase.TagUseCase

@Module
@InstallIn(ViewModelComponent::class)
object TagModule {

    @Provides
    fun provideTagImpl(): TagRepositoryImpl {
        return TagRepositoryImpl()
    }

    @Provides
    fun provideTagUseCase(): TagUseCase {
        return TagUseCase(provideTagImpl())
    }

}