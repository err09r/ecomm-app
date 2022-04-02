package com.app.ecommerceapp.data.mappers

import com.app.ecommerceapp.data.local.entities.HomeContentEntity
import com.app.ecommerceapp.data.remote.dto.HomeContentDto
import com.app.ecommerceapp.domain.models.HomeContent
import com.app.ecommerceapp.domain.util.HomeMapper

object HomeContentMapper : HomeMapper<HomeContentDto, HomeContent, HomeContentEntity> {

    override fun mapToDomainModel(entity: HomeContentEntity): HomeContent {
        with(entity) {
            return HomeContent(
                hotItems = hotItems.map { HotMapper.mapToDomainModel(it) },
                bestSellers = bestSellers.map { BestSellerMapper.mapToDomainModel(it) }
            )
        }
    }

    override fun mapToEntity(dto: HomeContentDto): HomeContentEntity {
        with(dto) {
            return HomeContentEntity(
                hotItems = hotItems.map { HotMapper.mapToEntity(it) },
                bestSellers = bestSellers.map { BestSellerMapper.mapToEntity(it) }
            )
        }
    }
}