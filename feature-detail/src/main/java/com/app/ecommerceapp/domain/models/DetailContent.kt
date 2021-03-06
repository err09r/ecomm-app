package com.app.ecommerceapp.domain.models

data class DetailContent(
    val processor: String,
    val camera: String,
    val isFavorites: Boolean,
    val price: Int,
    val rating: Float,
    val sd: String,
    val ram: String,
    val title: String,
    val images: List<String>,
    val colors: List<String>,
    val capacity: List<String>
)