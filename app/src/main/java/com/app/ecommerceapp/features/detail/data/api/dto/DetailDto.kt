package com.app.ecommerceapp.features.detail.data.api.dto

import com.app.ecommerceapp.features.detail.domain.models.DetailItem
import com.google.gson.annotations.SerializedName

data class DetailDto(
    @SerializedName("_id")
    val id: String,
    @SerializedName("CPU")
    val processor: String,
    @SerializedName("camera")
    val camera: String,
    @SerializedName("isFavorites")
    val isFavorites: Boolean,
    @SerializedName("price")
    val price: Int,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("sd")
    val sd: String,
    @SerializedName("ssd")
    val ram: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("color")
    val colors: List<String>,
    @SerializedName("capacity")
    val capacity: List<String>
) {
    fun toDetailItem(): DetailItem {
        return DetailItem(
            id = id,
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