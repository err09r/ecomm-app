package com.app.ecommerceapp.di

import android.content.Context
import androidx.room.Room
import com.app.ecommerceapp.data.local.converters.CartConverters
import com.app.ecommerceapp.data.local.dao.CartDao
import com.app.ecommerceapp.data.local.database.CartDatabase
import com.app.ecommerceapp.data.remote.api.ShopCartApi
import com.app.ecommerceapp.data.repository.CartRepositoryImpl
import com.app.ecommerceapp.domain.repository.CartRepository
import com.app.ecommerceapp.util.constants.CartConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideCartDatabase(
        @ApplicationContext context: Context,
        cartConverters: CartConverters
    ): CartDatabase {
        return Room.databaseBuilder(
            context,
            CartDatabase::class.java,
            DATABASE_NAME
        )
            .addTypeConverter(cartConverters)
            .build()
    }

    @Singleton
    @Provides
    fun provideCartDao(database: CartDatabase): CartDao = database.cartDao()

    @Singleton
    @Provides
    fun provideShopCartApi(retrofit: Retrofit): ShopCartApi {
        return retrofit.create(ShopCartApi::class.java)
    }
}