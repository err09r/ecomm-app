package com.app.ecommerceapp.di

import com.app.ecommerceapp.data.api.ShopCartApi
import com.app.ecommerceapp.data.repository.CartRepositoryImpl
import com.app.ecommerceapp.domain.repository.CartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartDataModule {

    @Provides
    @Singleton
    fun provideCartRepository(api: ShopCartApi): CartRepository {
        return CartRepositoryImpl(api)
    }
}