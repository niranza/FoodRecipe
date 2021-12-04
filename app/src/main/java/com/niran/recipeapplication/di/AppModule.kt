package com.niran.recipeapplication.di

import com.niran.recipeapplication.common.BASE_URL
import com.niran.recipeapplication.data.network.apis.RecipeApi
import com.niran.recipeapplication.data.repositories.RecipeRepositoryImpl
import com.niran.recipeapplication.domain.repositories.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRecipeApi(): RecipeApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(RecipeApi::class.java)

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeApi: RecipeApi
    ): RecipeRepository = RecipeRepositoryImpl(recipeApi)
}