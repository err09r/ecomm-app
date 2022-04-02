package com.app.ecommerceapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.ecommerceapp.data.local.DetailLocalDataSource
import com.app.ecommerceapp.data.local.entities.DetailContentEntity

@Dao
interface DetailDao : DetailLocalDataSource {

    @Query("SELECT * FROM detailcontententity")
    override fun getDetailContent(): List<DetailContentEntity>

    @Insert
    override fun insertDetailContent(entity: DetailContentEntity)
}