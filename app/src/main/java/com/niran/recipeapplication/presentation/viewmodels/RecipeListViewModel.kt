package com.niran.recipeapplication.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niran.recipeapplication.common.PAGE_SIZE
import com.niran.recipeapplication.core.common.Resource
import com.niran.recipeapplication.core.common.StateHolder
import com.niran.recipeapplication.domain.enums.FoodCategory
import com.niran.recipeapplication.domain.models.Recipe
import com.niran.recipeapplication.domain.use_cases.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeUseCases: RecipeUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _query = mutableStateOf("")
    var query: String
        get() = _query.value
        set(value) = with(_query) {
            savedStateHandle.set(STATE_KEY_QUERY, value)
            this.value = value
        }

    private val _selectedFoodCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    var selectedFoodCategory: FoodCategory?
        get() = _selectedFoodCategory.value
        set(value) = with(_selectedFoodCategory) {
            savedStateHandle.set(STATE_KEY_SELECTED_CATEGORY, value)
            this.value = value
        }

    var categoryPositionWithOffset: Pair<Int, Int>? = null
        set(value) {
            savedStateHandle.set(STATE_KEY_CATEGORY_POSITION, value)
            field = value
        }

    private val _recipesState = mutableStateOf(StateHolder<List<Recipe>>())
    val recipesState get() = _recipesState.value

    private var page = 1
        set(value) {
            savedStateHandle.set(STATE_KEY_PAGE, value)
            field = value
        }

    init {
        restoreProcessDeath()
        search()
    }

    fun onScrollPosition(scrollPosition: Int) {
        if ((scrollPosition + 1) >= page * PAGE_SIZE) {
            page++
            searchRecipes(page)
        }
    }

    fun search() {
        page = 1
        _recipesState.value = StateHolder()
        searchRecipes(page)
    }

    fun showShimmer() = _recipesState.value.isLoading && _recipesState.value.data.isNullOrEmpty()

    fun showPaginate() = _recipesState.value.isLoading && !_recipesState.value.data.isNullOrEmpty()

    private fun searchRecipes(page: Int) = recipeUseCases.searchRecipes(
        page, _query.value, _recipesState.value.data ?: listOf()
    )
        .onEach { resource ->
            when (resource) {
                is Resource.Loading -> _recipesState.value =
                    StateHolder(isLoading = true, data = resource.data)
                is Resource.Success -> _recipesState.value =
                    StateHolder(data = resource.data)
                is Resource.Error -> _recipesState.value =
                    StateHolder(errorMessageId = resource.messageId, data = resource.data)
            }
        }.launchIn(viewModelScope)

    private fun restoreProcessDeath() {
        savedStateHandle.get<Int>(STATE_KEY_PAGE)
            ?.let { page = it }
        savedStateHandle.get<String>(STATE_KEY_QUERY)
            ?.let { _query.value = it }
        savedStateHandle.get<Pair<Int, Int>>(STATE_KEY_CATEGORY_POSITION)
            ?.let { categoryPositionWithOffset = it }
        savedStateHandle.get<FoodCategory>(STATE_KEY_SELECTED_CATEGORY)
            ?.let { _selectedFoodCategory.value = it }
    }

    companion object {
        private const val TAG = "RecipeListViewModel"
        private const val STATE_KEY_PAGE = "$TAG.page.key"
        private const val STATE_KEY_QUERY = "$TAG.query.key"
        private const val STATE_KEY_CATEGORY_POSITION = "$TAG.category_position.key"
        private const val STATE_KEY_SELECTED_CATEGORY = "$TAG.selected_category.key"
    }
}