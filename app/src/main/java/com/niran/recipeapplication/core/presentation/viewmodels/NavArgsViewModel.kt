package com.niran.recipeapplication.core.presentation.viewmodels

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import com.niran.recipeapplication.core.common.BUNDLE_ARGS

abstract class NavArgsViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val arguments get() = savedStateHandle.get<Bundle>(BUNDLE_ARGS)

    @MainThread
    inline fun <reified Args : NavArgs> navArgs() = NavArgsLazy(Args::class) {
        arguments ?: throw IllegalStateException("ViewModel $this has null arguments")
    }
}