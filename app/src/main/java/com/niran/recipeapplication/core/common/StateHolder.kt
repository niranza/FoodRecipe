package com.niran.recipeapplication.core.common

import androidx.annotation.StringRes

data class StateHolder<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    @StringRes val errorMessageId: Int? = null,
)