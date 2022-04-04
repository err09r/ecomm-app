package com.app.ecommerceapp.di

import com.app.ecommerceapp.data.local.dao.CartDao
import com.app.ecommerceapp.data.remote.api.ShopCartApi
import com.app.ecommerceapp.data.repository.CartRepositoryImpl
import com.app.ecommerceapp.domain.repository.CartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartDataModule {

    @Provides
    @Singleton
    fun provideCartRepository(api: ShopCartApi, dao: CartDao): CartRepository {
        return CartRepositoryImpl(api, dao)
    }

    @Singleton
    @Provides
    fun provideShopCartApi(retrofit: Retrofit): ShopCartApi {
        return retrofit.create(ShopCartApi::class.java)
    }
}