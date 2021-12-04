package com.niran.recipeapplication.core.common.extensions

inline fun String.whenNotNullOrBlank(action: (String) -> Unit) {
    if (!isNullOrBlank()) action(this)
}