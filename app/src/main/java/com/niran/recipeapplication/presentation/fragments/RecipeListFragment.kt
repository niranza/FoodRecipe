package com.niran.recipeapplication.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.niran.recipeapplication.presentation.composables.CircularIndeterminateProgressBar
import com.niran.recipeapplication.presentation.composables.items.ItemRecipe
import com.niran.recipeapplication.presentation.composables.LoadingRecipeListShimmer
import com.niran.recipeapplication.presentation.composables.SearchAppBar
import com.niran.recipeapplication.presentation.theme.RecipeApplicationTheme
import com.niran.recipeapplication.presentation.viewmodels.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
@ExperimentalComposeUiApi
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            RecipeApplicationTheme {

                val context = LocalContext.current
                val keyboardController = LocalSoftwareKeyboardController.current
                val categoryScrollState = rememberLazyListState()

                val saveCategoryScrollPosition = {
                    viewModel.categoryPositionWithOffset = Pair(
                        categoryScrollState.firstVisibleItemIndex,
                        categoryScrollState.firstVisibleItemScrollOffset
                    )
                }

                val restoreCategoryScrollPosition = suspend {
                    viewModel.categoryPositionWithOffset?.apply {
                        categoryScrollState.scrollToItem(first, second)
                    }
                }

                LaunchedEffect(true) {
                    lifecycleScope.launch {
                        restoreCategoryScrollPosition()
                    }
                }

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
                            saveCategoryScrollPosition()
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
                            ItemRecipe(recipe = recipe) {
                                navigateToDetailFragment(recipe.id)
                            }
                        }

                        if (viewModel.showPaginate()) item {
                            CircularIndeterminateProgressBar()
                        }
                    }
                }
            }
        }
    }

    private fun navigateToDetailFragment(recipeId: Int) = findNavController().navigate(
        RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(recipeId)
    )
}