package com.app.ecommerceapp.di

import com.app.ecommerceapp.data.remote.api.ShopCartApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartDomainModule {

    @Singleton
    @Provides
    fun provideShopCartApi(retrofit: Retrofit): ShopCartApi {
        return retrofit.create(ShopCartApi::class.java)
    }
}