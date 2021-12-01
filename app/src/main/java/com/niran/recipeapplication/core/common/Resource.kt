package com.niran.recipeapplication.core.common

import androidx.annotation.StringRes

sealed class Resource<out T>(data: T?, @StringRes message: Int?) {
    class Loading<T>(val data: T? = null) : Resource<T>(data, null)
    class Success<T>(val data: T, @StringRes val message: Int? = null) : Resource<T>(data, message)
    class Error<T>(@StringRes val messageId: Int, val data: T? = null) : Resource<T>(data, messageId)
}