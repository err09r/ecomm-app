package com.app.ecommerceapp.util.extensions

import com.app.ecommerceapp.presentation.models.ImageItem

fun String.toImageItem(): ImageItem {
    return ImageItem(
        src = this
    )
}