package com.niran.recipeapplication.data.repositories

import com.niran.recipeapplication.data.network.apis.RecipeApi
import com.niran.recipeapplication.data.network.models.RecipeDto
import com.niran.recipeapplication.data.network.responses.RecipeSearchResponse
import com.niran.recipeapplication.domain.models.Recipe
import com.niran.recipeapplication.domain.repositories.RecipeRepository
import javax.inject.Inject
import javax.inject.Singleton

class RecipeRepositoryImpl(
    private val api: RecipeApi
) : RecipeRepository {

    override suspend fun search(page: Int, query: String): List<Recipe>? =
        api.search(page, query).results?.map { it.toRecipe() }

    override suspend fun get(token: String, id: Int): RecipeDto = api.get(token, id)
}