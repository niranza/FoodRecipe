package com.niran.recipeapplication.data.network.responses


import androidx.annotation.Keep
import com.niran.recipeapplication.data.network.models.RecipeDto

@Keep
data class RecipeSearchResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<RecipeDto>?
)