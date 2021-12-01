package com.niran.recipeapplication.data.network.responses


import com.google.gson.annotations.SerializedName
import com.niran.recipeapplication.data.network.models.RecipeDto

data class RecipeSearchResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<RecipeDto>?
)