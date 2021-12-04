package com.niran.recipeapplication.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.niran.recipeapplication.presentation.screens.RecipeListScreen
import com.niran.recipeapplication.presentation.theme.RecipeApplicationTheme
import com.niran.recipeapplication.presentation.viewmodels.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalComposeUiApi // For Keyboard Controller instance
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            RecipeApplicationTheme {
                RecipeListScreen(viewModel = viewModel, navController = findNavController())
            }
        }
    }
}