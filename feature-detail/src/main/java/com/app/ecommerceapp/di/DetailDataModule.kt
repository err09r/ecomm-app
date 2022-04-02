package com.app.ecommerceapp.di

import android.content.Context
import androidx.room.Room
import com.app.ecommerceapp.data.local.converters.DetailConverters
import com.app.ecommerceapp.data.local.dao.DetailDao
import com.app.ecommerceapp.data.local.database.DetailDatabase
import com.app.ecommerceapp.data.remote.api.ShopDetailApi
import com.app.ecommerceapp.data.repository.DetailRepositoryImpl
import com.app.ecommerceapp.domain.repository.DetailRepository
import com.app.ecommerceapp.util.constants.DetailConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailDataModule {

    @Provides
    @Singleton
    fun provideDetailRepository(api: ShopDetailApi, dao: DetailDao): DetailRepository {
        return DetailRepositoryImpl(api, dao)
    }

    @Singleton
    @Provides
    fun provideDetailDatabase(
        @ApplicationContext context: Context,
        detailConverters: DetailConverters
    ): DetailDatabase {
        return Room.databaseBuilder(
            context,
            DetailDatabase::class.java,
            DATABASE_NAME
        )
            .addTypeConverter(detailConverters)
            .build()
    }

    @Singleton
    @Provides
    fun provideDetailDao(database: DetailDatabase): DetailDao = database.detailDao()

    @Singleton
    @Provides
    fun provideShopDetailApi(retrofit: Retrofit): ShopDetailApi {
        return retrofit.create(ShopDetailApi::class.java)
    }
}