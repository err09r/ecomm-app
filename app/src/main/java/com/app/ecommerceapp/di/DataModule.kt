package com.app.ecommerceapp.di

import com.app.ecommerceapp.features.cart.data.api.ShopCartApi
import com.app.ecommerceapp.features.cart.data.repository.CartRepositoryImpl
import com.app.ecommerceapp.features.cart.domain.repository.CartRepository
import com.app.ecommerceapp.features.detail.data.api.ShopDetailApi
import com.app.ecommerceapp.features.detail.data.repository.DetailRepositoryImpl
import com.app.ecommerceapp.features.detail.domain.repository.DetailRepository
import com.app.ecommerceapp.features.home.data.api.ShopHomeApi
import com.app.ecommerceapp.features.home.data.repository.HomeRepositoryImpl
import com.app.ecommerceapp.features.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideHomeRepository(api: ShopHomeApi): HomeRepository {
        return HomeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(api: ShopDetailApi): DetailRepository {
        return DetailRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCartRepository(api: ShopCartApi): CartRepository {
        return CartRepositoryImpl(api)
    }
}