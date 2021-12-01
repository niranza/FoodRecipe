package com.niran.recipeapplication.domain.use_cases

import javax.inject.Inject

data class RecipeUseCases @Inject constructor(
    val getRecipe: GetRecipe,
    val searchRecipes: SearchRecipes,
)
