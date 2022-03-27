package com.app.ecommerceapp.data.api.dto

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
)