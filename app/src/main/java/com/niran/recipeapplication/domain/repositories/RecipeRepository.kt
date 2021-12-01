package com.niran.recipeapplication.domain.repositories

import com.niran.recipeapplication.data.network.models.RecipeDto
import com.niran.recipeapplication.domain.models.Recipe

interface RecipeRepository {

    suspend fun search(page: Int, query: String): List<Recipe>?

    suspend fun get(token: String, id: Int): RecipeDto
}