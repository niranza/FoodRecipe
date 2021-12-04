package com.niran.recipeapplication.domain.models

data class Recipe(
    val title: String? = null,
    val publisher: String? = null,
    val featuredImage: String? = null,
    val rating: Int? = null,
    val sourceUrl: String? = null,
    val description: String? = null,
    val cookingInstructions: String? = null,
    val ingredients: List<String>? = null,
    val dateAdded: String? = null,
    val dateUpdated: String? = null,
    val id: Int? = null,
)