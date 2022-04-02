package com.app.ecommerceapp.data.mappers

import com.app.ecommerceapp.data.local.entities.DetailContentEntity
import com.app.ecommerceapp.data.remote.dto.DetailContentDto
import com.app.ecommerceapp.domain.models.DetailContent
import com.app.ecommerceapp.domain.util.DetailMapper

object DetailContentMapper : DetailMapper<DetailContentDto, DetailContent, DetailContentEntity> {

    override fun mapToDomainModel(entity: DetailContentEntity): DetailContent {
        with(entity) {
            return DetailContent(
                processor = processor,
                camera = camera,
                isFavorites = isFavorites,
                price = price,
                rating = rating,
                sd = sd,
                ram = ram,
                title = title,
                images = images,
                colors = colors,
                capacity = capacity
            )
        }
    }

    override fun mapToEntity(dto: DetailContentDto): DetailContentEntity {
        with(dto) {
            return DetailContentEntity(
                processor = processor,
                camera = camera,
                isFavorites = isFavorites,
                price = price,
                rating = rating,
                sd = sd,
                ram = ram,
                title = title,
                images = images,
                colors = colors,
                capacity = capacity
            )
        }
    }
}