package com.app.ecommerceapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.ecommerceapp.data.local.HomeLocalDataSource
import com.app.ecommerceapp.data.local.entities.HomeContentEntity

@Dao
interface HomeDao : HomeLocalDataSource {

    @Query("SELECT * FROM homecontententity")
    override fun getHomeContent(): List<HomeContentEntity>

    @Insert
    override fun insertHomeContent(entity: HomeContentEntity)
}