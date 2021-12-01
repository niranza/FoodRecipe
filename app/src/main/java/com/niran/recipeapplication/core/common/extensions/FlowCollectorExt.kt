package com.niran.recipeapplication.core.common.extensions

import com.niran.recipeapplication.R
import com.niran.recipeapplication.core.common.Resource
import kotlinx.coroutines.flow.FlowCollector

suspend fun <T> FlowCollector<Resource<T>>.emitBasicError(data: T) {
    emit(Resource.Error(R.string.error, data))
}