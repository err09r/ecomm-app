package com.app.ecommerceapp.di

import com.app.ecommerceapp.features.cart.data.api.ShopCartApi
import com.app.ecommerceapp.features.detail.data.api.ShopDetailApi
import com.app.ecommerceapp.features.home.data.api.ShopHomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideShopHomeApi(retrofit: Retrofit): ShopHomeApi {
        return retrofit.create(ShopHomeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideShopDetailApi(retrofit: Retrofit): ShopDetailApi {
        return retrofit.create(ShopDetailApi::class.java)
    }

    @Singleton
    @Provides
    fun provideShopCartApi(retrofit: Retrofit): ShopCartApi {
        return retrofit.create(ShopCartApi::class.java)
    }
}