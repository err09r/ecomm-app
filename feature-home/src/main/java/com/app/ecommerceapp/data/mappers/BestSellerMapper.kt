package com.app.ecommerceapp.data.mappers

import com.app.ecommerceapp.data.local.entities.BestSellerEntity
import com.app.ecommerceapp.data.remote.dto.BestSellerDto
import com.app.ecommerceapp.domain.models.BestSellerItem
import com.app.ecommerceapp.domain.util.HomeMapper

object BestSellerMapper : HomeMapper<BestSellerDto, BestSellerItem, BestSellerEntity> {

    override fun mapToDomainModel(entity: BestSellerEntity): BestSellerItem {
        with(entity) {
            return BestSellerItem(
                id = id,
                fullPrice = fullPrice,
                finalPrice = finalPrice,
                title = title,
                src = src,
                isFavorites = isFavorites
            )
        }
    }

    override fun mapToEntity(dto: BestSellerDto): BestSellerEntity {
        with(dto) {
            return BestSellerEntity(
                id = id,
                fullPrice = fullPrice,
                finalPrice = finalPrice,
                title = title,
                src = src,
                isFavorites = isFavorites
            )
        }
    }
}