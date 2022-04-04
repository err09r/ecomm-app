package com.app.ecommerceapp.di

import com.app.ecommerceapp.data.local.dao.HomeDao
import com.app.ecommerceapp.data.remote.api.ShopHomeApi
import com.app.ecommerceapp.data.repository.HomeRepositoryImpl
import com.app.ecommerceapp.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeDataModule {

    @Singleton
    @Provides
    fun provideHomeRepository(api: ShopHomeApi, dao: HomeDao): HomeRepository {
        return HomeRepositoryImpl(api, dao)
    }

    @Singleton
    @Provides
    fun provideShopHomeApi(retrofit: Retrofit): ShopHomeApi {
        return retrofit.create(ShopHomeApi::class.java)
    }
}