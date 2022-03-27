package com.app.ecommerceapp.data.api.dto.mappers

import com.app.ecommerceapp.data.api.dto.DetailContentDto
import com.app.ecommerceapp.domain.models.DetailContent
import com.app.ecommerceapp.domain.utils.DetailMapper

object DetailContentMapper : DetailMapper<DetailContentDto, DetailContent> {

    override fun mapToDomainModel(dto: DetailContentDto): DetailContent {
        with(dto) {
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
}