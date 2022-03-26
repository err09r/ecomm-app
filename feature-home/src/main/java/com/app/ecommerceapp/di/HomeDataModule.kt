package com.app.ecommerceapp.di

import com.app.ecommerceapp.data.api.ShopHomeApi
import com.app.ecommerceapp.data.repository.HomeRepositoryImpl
import com.app.ecommerceapp.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object HomeDataModule {

    @Provides
    @Singleton
    fun provideHomeRepository(api: ShopHomeApi): HomeRepository {
        return HomeRepositoryImpl(api)
    }
}