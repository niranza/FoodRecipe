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
import javax.xml.transform.TransformerException

class GetRecipe @Inject constructor(
    private val repository: RecipeRepository
) {

    operator fun invoke(token: String, id: Int): Flow<Resource<Recipe>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.get(token, id).toRecipe()))
        } catch (e: IOException) {
            emit(Resource.Error(R.string.error))
        } catch (e: HttpException) {
            emit(Resource.Error(R.string.error))
        }
    }
}