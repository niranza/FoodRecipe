package com.niran.recipeapplication.domain.models

data class Recipe(
    val title: String = "",
    val publisher: String = "",
    val featuredImage: String? = null,
    val rating: Int? = null,
    val sourceUrl: String = "",
    val description: String = "",
    val cookingInstructions: String = "",
    val ingredients: List<String> = listOf(),
    val dateAdded: String = "",
    val dateUpdated: String = "",
    val id: Int = -1,
)