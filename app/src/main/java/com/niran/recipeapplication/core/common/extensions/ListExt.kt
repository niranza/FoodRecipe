package com.niran.recipeapplication.core.common.extensions

inline fun <T> List<T>.whenNotNullOrEmpty(action: (List<T>) -> Unit) {
    if (!isNullOrEmpty()) action(this)
}