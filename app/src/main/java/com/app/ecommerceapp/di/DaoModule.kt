package com.app.ecommerceapp.di

import com.app.ecommerceapp.data.local.dao.CartDao
import com.app.ecommerceapp.data.local.dao.DetailDao
import com.app.ecommerceapp.data.local.dao.HomeDao
import com.app.ecommerceapp.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Singleton
    @Provides
    fun provideHomeDao(database: AppDatabase): HomeDao = database.homeDao()

    @Singleton
    @Provides
    fun provideDetailDao(database: AppDatabase): DetailDao = database.detailDao()

    @Singleton
    @Provides
    fun provideCartDao(database: AppDatabase): CartDao = database.cartDao()
}