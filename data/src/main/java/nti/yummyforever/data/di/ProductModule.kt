package nti.yummyforever.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import nti.yummyforever.data.repository.ProductRepositoryImpl
import nti.yummyforever.domain.repository.ProductRepository
import nti.yummyforever.domain.usecase.ProductUseCase

@Module
@InstallIn(ViewModelComponent::class)
object ProductModule {

    @Provides
    fun provideProductImpl():ProductRepositoryImpl{
        return ProductRepositoryImpl()
    }

    @Provides
    fun provideProductUseCase():ProductUseCase{
        return ProductUseCase(provideProductImpl())
    }

}