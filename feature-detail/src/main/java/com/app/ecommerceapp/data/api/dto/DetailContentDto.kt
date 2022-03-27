package com.app.ecommerceapp.data.api.dto

import com.google.gson.annotations.SerializedName

data class DetailContentDto(
    @SerializedName("CPU")
    val processor: String,

    @SerializedName("camera")
    val camera: String,

    @SerializedName("isFavorites")
    val isFavorites: Boolean,

    @SerializedName("price")
    val price: Int,

    @SerializedName("rating")
    val rating: Float,

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
)