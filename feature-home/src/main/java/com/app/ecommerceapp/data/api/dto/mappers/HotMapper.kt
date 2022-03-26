package com.app.ecommerceapp.data.api.dto.mappers

import com.app.ecommerceapp.data.api.dto.HotDto
import com.app.ecommerceapp.domain.models.HotItem
import com.app.ecommerceapp.domain.utils.HomeMapper

object HotMapper : HomeMapper<HotDto, HotItem> {

    override fun mapToDomainModel(dto: HotDto): HotItem {
        with(dto) {
            return HotItem(
                id = id,
                isNew = isNew,
                title = title,
                subtitle = subtitle,
                src = src,
                isBuy = isBuy
            )
        }
    }
}