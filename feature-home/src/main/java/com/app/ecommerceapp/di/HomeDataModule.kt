package com.app.ecommerceapp.di

import android.content.Context
import androidx.room.Room
import com.app.ecommerceapp.data.local.converters.HomeConverters
import com.app.ecommerceapp.data.local.dao.HomeDao
import com.app.ecommerceapp.data.local.database.HomeDatabase
import com.app.ecommerceapp.data.remote.api.ShopHomeApi
import com.app.ecommerceapp.data.repository.HomeRepositoryImpl
import com.app.ecommerceapp.domain.repository.HomeRepository
import com.app.ecommerceapp.util.constants.HomeConstants.DATABASE_NAME
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideHomeDatabase(
        @ApplicationContext context: Context,
        homeConverters: HomeConverters
    ): HomeDatabase {
        return Room.databaseBuilder(
            context,
            HomeDatabase::class.java,
            DATABASE_NAME
        )
            .addTypeConverter(homeConverters)
            .build()
    }

    @Singleton
    @Provides
    fun provideHomeDao(database: HomeDatabase): HomeDao = database.homeDao()

    @Singleton
    @Provides
    fun provideShopHomeApi(retrofit: Retrofit): ShopHomeApi {
        return retrofit.create(ShopHomeApi::class.java)
    }
}