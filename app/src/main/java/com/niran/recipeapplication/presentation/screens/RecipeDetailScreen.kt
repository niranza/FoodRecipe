package com.niran.recipeapplication.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.niran.recipeapplication.R
import com.niran.recipeapplication.core.presentation.composables.BackButton
import com.niran.recipeapplication.presentation.composables.LoadingRecipeDetailShimmer
import com.niran.recipeapplication.presentation.composables.RecipeDetailView
import com.niran.recipeapplication.presentation.viewmodels.RecipeDetailViewModel

@Composable
fun RecipeDetailScreen(
    viewModel: RecipeDetailViewModel,
    navController: NavController,
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            scaffoldState.snackbarHostState
        },
        topBar = {
            TopBar(
                navController = navController,
                recipeTitle = viewModel.recipeTitle,
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (viewModel.recipeState.isLoading) LoadingRecipeDetailShimmer()
            viewModel.recipeState.data?.let { recipe -> RecipeDetailView(recipe = recipe) }
        }
    }
}

@Composable
private fun TopBar(
    navController: NavController,
    recipeTitle: String?,
) {
    Surface {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton {
                navController.navigateUp()
            }
            Text(
                text = recipeTitle ?: stringResource(id = R.string.recipe_details),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h3,
            )
        }
    }
}