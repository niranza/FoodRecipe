package com.niran.recipeapplication.data.network.apis

import com.niran.recipeapplication.core.common.Constants
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
        @Header("Authorization") token: String = Constants.TOKEN,
        ): RecipeSearchResponse

    @GET("get")
    suspend fun get(
        @Header("Authorization") token: String,
        @Query("id") id: Int,
    ): RecipeDto
}