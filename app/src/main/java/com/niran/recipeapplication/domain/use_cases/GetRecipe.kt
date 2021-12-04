package com.niran.recipeapplication.domain.use_cases

import com.niran.recipeapplication.R
import com.niran.recipeapplication.core.common.Resource
import com.niran.recipeapplication.domain.models.Recipe
import com.niran.recipeapplication.domain.repositories.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecipe @Inject constructor(
    private val repository: RecipeRepository
) {

    operator fun invoke(id: Int): Flow<Resource<Recipe>> = flow {
        emit(Resource.Loading())
        try {
            repository.get(id)?.let { recipe ->
                emit(Resource.Success(recipe))
            } ?: emit(Resource.Error(R.string.error))
        } catch (e: IOException) {
            emit(Resource.Error(R.string.error))
        } catch (e: HttpException) {
            emit(Resource.Error(R.string.error))
        }
    }
}