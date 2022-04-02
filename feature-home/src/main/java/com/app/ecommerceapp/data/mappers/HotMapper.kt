package com.app.ecommerceapp.data.mappers

import com.app.ecommerceapp.data.local.entities.HotEntity
import com.app.ecommerceapp.data.remote.dto.HotDto
import com.app.ecommerceapp.domain.models.HotItem
import com.app.ecommerceapp.domain.util.HomeMapper

object HotMapper : HomeMapper<HotDto, HotItem, HotEntity> {

    override fun mapToDomainModel(entity: HotEntity): HotItem {
        with(entity) {
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

    override fun mapToEntity(dto: HotDto): HotEntity {
        with(dto) {
            return HotEntity(
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