package com.niran.recipeapplication.presentation.viewmodels

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import com.niran.recipeapplication.core.common.Resource
import com.niran.recipeapplication.core.common.StateHolder
import com.niran.recipeapplication.core.presentation.viewmodels.NavArgsViewModel
import com.niran.recipeapplication.domain.models.Recipe
import com.niran.recipeapplication.domain.use_cases.RecipeUseCases
import com.niran.recipeapplication.presentation.fragments.RecipeDetailFragmentArgs
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeUseCases: RecipeUseCases,
    savedStateHandle: SavedStateHandle,
) : NavArgsViewModel(savedStateHandle) {

    private val navArgs: RecipeDetailFragmentArgs by navArgs()
    private val recipeId get() = navArgs.recipeId
    val recipeTitle get() = navArgs.recipeTitle

    private val _recipeState = mutableStateOf(StateHolder<Recipe>())
    val recipeState get() = _recipeState.value

    init {
        getRecipe()
    }

    private fun getRecipe() = recipeUseCases.getRecipe(recipeId).onEach { resource ->
        when (resource) {
            is Resource.Loading -> _recipeState.value =
                StateHolder(isLoading = true)
            is Resource.Success -> _recipeState.value =
                StateHolder(data = resource.data)
            is Resource.Error -> _recipeState.value =
                StateHolder(errorMessageId = resource.messageId)
        }
    }.launchIn(viewModelScope)
}