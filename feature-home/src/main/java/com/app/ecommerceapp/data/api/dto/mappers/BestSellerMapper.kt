package com.app.ecommerceapp.data.api.dto.mappers

import com.app.ecommerceapp.data.api.dto.BestSellerDto
import com.app.ecommerceapp.domain.models.BestSellerItem
import com.app.ecommerceapp.domain.utils.HomeMapper

object BestSellerMapper : HomeMapper<BestSellerDto, BestSellerItem> {

    override fun mapToDomainModel(dto: BestSellerDto): BestSellerItem {
        with(dto) {
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
}