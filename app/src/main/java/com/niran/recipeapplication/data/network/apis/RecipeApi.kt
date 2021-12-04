package com.niran.recipeapplication.data.network.apis

import com.niran.recipeapplication.common.TOKEN
import com.niran.recipeapplication.data.network.models.RecipeDto
import com.niran.recipeapplication.data.network.responses.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeApi {

    @GET("search")
    suspend fun search(
        @Query("page") page: Int,
        @Query("query") query: String,
        @Header("Authorization") token: String = TOKEN,
    ): RecipeSearchResponse?

    @GET("get")
    suspend fun get(
        @Query("id") id: Int,
        @Header("Authorization") token: String = TOKEN,
    ): RecipeDto?
}