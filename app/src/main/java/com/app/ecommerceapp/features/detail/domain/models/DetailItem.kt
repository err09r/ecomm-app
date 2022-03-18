package com.app.ecommerceapp.features.detail.domain.models

data class DetailItem(
    val id: String,
    val processor: String,
    val camera: String,
    val isFavorites: Boolean,
    val price: Int,
    val rating: Int,
    val sd: String,
    val ram: String,
    val title: String,
    val images: List<String>,
    val colors: List<String>,
    val capacity: List<String>
)