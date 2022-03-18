package com.app.ecommerceapp.features.home.data.api.dto

import com.app.ecommerceapp.features.home.domain.models.HotItem
import com.google.gson.annotations.SerializedName

data class HotDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("picture")
    val src: String,
    @SerializedName("is_buy")
    val isBuy: Boolean
) {
    fun toHotItem(): HotItem {
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