package com.app.ecommerceapp.domain.models

data class HotItem(
    val id: Int,
    val isNew: Boolean,
    val title: String,
    val subtitle: String,
    val src: String,
    val isBuy: Boolean
)