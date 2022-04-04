package com.app.ecommerceapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.ecommerceapp.data.local.converters.CartConverters
import com.app.ecommerceapp.data.local.converters.DetailConverters
import com.app.ecommerceapp.data.local.converters.HomeConverters
import com.app.ecommerceapp.database.AppDatabase
import com.app.ecommerceapp.util.constants.CommonConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        homeConverters: HomeConverters,
        detailConverters: DetailConverters,
        cartConverters: CartConverters,
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .addTypeConverter(homeConverters)
            .addTypeConverter(detailConverters)
            .addTypeConverter(cartConverters)
            .build()
    }
}