package com.niran.recipeapplication.data.network.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.niran.recipeapplication.domain.models.Recipe

@Keep
data class RecipeDto(
    @SerializedName("cooking_instructions")
    val cookingInstructions: String?,
    @SerializedName("date_added")
    val dateAdded: String?,
    @SerializedName("date_updated")
    val dateUpdated: String?,
    val description: String?,
    @SerializedName("featured_image")
    val featuredImage: String?,
    val ingredients: List<String>?,
    @SerializedName("long_date_added")
    val longDateAdded: Int?,
    @SerializedName("long_date_updated")
    val longDateUpdated: Int?,
    val pk: Int?,
    val publisher: String?,
    val rating: Int?,
    @SerializedName("source_url")
    val sourceUrl: String?,
    val title: String?
) {
    fun toRecipe() = Recipe(
        title,
        publisher,
        featuredImage,
        rating,
        sourceUrl,
        description,
        cookingInstructions,
        ingredients,
        dateAdded,
        dateUpdated,
        pk,
        )
}