package com.niran.recipeapplication.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.niran.recipeapplication.core.presentation.composables.CircularIndeterminateProgressBar
import com.niran.recipeapplication.presentation.composables.LoadingRecipeListShimmer
import com.niran.recipeapplication.presentation.composables.SearchAppBar
import com.niran.recipeapplication.presentation.composables.items.ItemRecipe
import com.niran.recipeapplication.presentation.fragments.RecipeListFragmentDirections
import com.niran.recipeapplication.presentation.viewmodels.RecipeListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi // For Keyboard Controller instance
@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel,
    navController: NavController,
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val categoryScrollState = rememberLazyListState()

    LaunchedEffect(true) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.restoreCategoryScrollPosition(categoryScrollState)
        }
    }

    Scaffold {
        Column {

            SearchAppBar(
                query = viewModel.query,
                onQueryChanged = {
                    viewModel.query = it
                },
                onSearch = {
                    viewModel.selectedFoodCategory = null
                    viewModel.search()
                    keyboardController?.hide()
                },
                categoryScrollState = categoryScrollState,
                isSelected = { foodCategory ->
                    viewModel.selectedFoodCategory == foodCategory
                },
                onSelectedCategoryChanged = { foodCategory ->
                    viewModel.saveCategoryScrollPosition(categoryScrollState)
                    viewModel.selectedFoodCategory = foodCategory
                    viewModel.query = context.getString(foodCategory.nameId)
                    viewModel.search()
                }
            )

            if (viewModel.showShimmer()) LoadingRecipeListShimmer(cardHeight = 230.dp)

            LazyColumn(
                contentPadding = PaddingValues(all = 6.dp),
            ) {
                itemsIndexed(
                    items = viewModel.recipesState.data ?: listOf()
                ) { index, recipe ->
                    viewModel.onScrollPosition(index)
                    recipe.id?.let { recipeId ->
                        ItemRecipe(recipe = recipe) {
                            navController.navigateToDetailFragment(recipeId, recipe.title)
                        }
                    }
                }

                if (viewModel.showPaginate()) item {
                    CircularIndeterminateProgressBar()
                }
            }
        }
    }
}

private fun NavController.navigateToDetailFragment(recipeId: Int, recipeTitle: String?) = navigate(
    RecipeListFragmentDirections
        .actionRecipeListFragmentToRecipeDetailFragment(recipeId, recipeTitle)
)

private fun RecipeListViewModel.saveCategoryScrollPosition(categoryScrollState: LazyListState) {
    categoryPositionWithOffset = Pair(
        categoryScrollState.firstVisibleItemIndex,
        categoryScrollState.firstVisibleItemScrollOffset
    )
}

private suspend fun RecipeListViewModel.restoreCategoryScrollPosition(
    categoryScrollState: LazyListState
) = categoryPositionWithOffset?.apply {
    categoryScrollState.scrollToItem(first, second)
}
