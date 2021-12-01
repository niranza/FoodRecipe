package com.niran.recipeapplication.domain.use_cases

import com.niran.recipeapplication.core.common.Resource
import com.niran.recipeapplication.core.common.extensions.emitBasicError
import com.niran.recipeapplication.domain.models.Recipe
import com.niran.recipeapplication.domain.repositories.RecipeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchRecipes @Inject constructor(
    private val repository: RecipeRepository
) {

    operator fun invoke(
        page: Int,
        query: String,
        currentData: List<Recipe> = listOf(),
    ): Flow<Resource<List<Recipe>>> = flow {
        emit(Resource.Loading(currentData))
        try {
            repository.search(page, query)?.let { recipeList ->
                val newData = currentData + recipeList
                emit(Resource.Success(newData))
            } ?: emitBasicError(currentData)
        } catch (e: IOException) {
            emitBasicError(currentData)
        } catch (e: HttpException) {
            emitBasicError(currentData)
        }
    }
}