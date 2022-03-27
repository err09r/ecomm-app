package com.app.ecommerceapp.di

import com.app.ecommerceapp.data.api.ShopDetailApi
import com.app.ecommerceapp.data.repository.DetailRepositoryImpl
import com.app.ecommerceapp.domain.repository.DetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailDataModule {

    @Provides
    @Singleton
    fun provideDetailRepository(api: ShopDetailApi): DetailRepository {
        return DetailRepositoryImpl(api)
    }
}