package com.app.ecommerceapp.util.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.reloadFragment() {
    this.findNavController().apply {
        currentDestination?.let { destination ->
            popBackStack(destinationId = destination.id, inclusive = false)
            navigate(resId = destination.id)
        }
    }
}