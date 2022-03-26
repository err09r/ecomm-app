package com.app.ecommerceapp.data.api.dto.mappers

import com.app.ecommerceapp.data.api.dto.HomeContentDto
import com.app.ecommerceapp.domain.models.HomeContent
import com.app.ecommerceapp.domain.utils.HomeMapper

object HomeContentMapper : HomeMapper<HomeContentDto, HomeContent> {

    override fun mapToDomainModel(dto: HomeContentDto): HomeContent {
        with(dto) {
            return HomeContent(
                hotItems = hotItems.map { HotMapper.mapToDomainModel(it) },
                bestSellers = bestSellers.map { BestSellerMapper.mapToDomainModel(it) }
            )
        }
    }
}