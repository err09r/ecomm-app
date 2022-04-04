package com.app.ecommerceapp.di

import com.app.ecommerceapp.data.local.dao.DetailDao
import com.app.ecommerceapp.data.remote.api.ShopDetailApi
import com.app.ecommerceapp.data.repository.DetailRepositoryImpl
import com.app.ecommerceapp.domain.repository.DetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailDataModule {

    @Provides
    @Singleton
    fun provideDetailRepository(api: ShopDetailApi, dao: DetailDao): DetailRepository {
        return DetailRepositoryImpl(api, dao)
    }

    @Singleton
    @Provides
    fun provideShopDetailApi(retrofit: Retrofit): ShopDetailApi {
        return retrofit.create(ShopDetailApi::class.java)
    }
}