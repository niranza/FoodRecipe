package com.niran.recipeapplication.core.common.extensions

inline fun <T> T.whenNull(action: () -> Unit) {
    if (this == null) action()
}