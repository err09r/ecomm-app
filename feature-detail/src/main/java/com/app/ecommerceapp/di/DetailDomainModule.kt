package com.app.ecommerceapp.di

import com.app.ecommerceapp.data.api.ShopDetailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DetailDomainModule {

    @Singleton
    @Provides
    fun provideShopDetailApi(retrofit: Retrofit): ShopDetailApi {
        return retrofit.create(ShopDetailApi::class.java)
    }
}