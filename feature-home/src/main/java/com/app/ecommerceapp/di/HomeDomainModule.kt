package com.app.ecommerceapp.di

import com.app.ecommerceapp.data.remote.api.ShopHomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeDomainModule {

    @Singleton
    @Provides
    fun provideShopHomeApi(retrofit: Retrofit): ShopHomeApi {
        return retrofit.create(ShopHomeApi::class.java)
    }
}