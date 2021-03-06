package com.app.ecommerceapp.util.extensions

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.NumberFormat
import java.util.Locale

fun Int.toSeparatedNumber(): String = NumberFormat.getInstance(Locale.US).format(this)

fun Int.toSeparatedNumberWithFractionalPart(): String {
    return "${NumberFormat.getInstance(Locale.US).format(this)}.00"
}

fun Int.toStringWithFractionalPart(): String = "$this.00"

fun Activity.changeNavigationBarColor(@ColorRes color: Int) {
    this.window.navigationBarColor = ResourcesCompat.getColor(resources, color, null)
}

fun View.hide() {
    this.isVisible = false
}

fun View.show() {
    this.isVisible = true
}

fun TextView.changeTextColor(@ColorRes color: Int) {
    this.setTextColor(ResourcesCompat.getColor(resources, color, null))
}

inline fun <reified T> Gson.fromJson(json: String): T =
    fromJson(json, object : TypeToken<T>() {}.type)