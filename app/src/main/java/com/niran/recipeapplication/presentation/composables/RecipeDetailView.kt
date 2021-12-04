package com.niran.recipeapplication.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.niran.recipeapplication.R
import com.niran.recipeapplication.core.common.extensions.whenNotNullOrBlank
import com.niran.recipeapplication.core.common.extensions.whenNotNullOrEmpty
import com.niran.recipeapplication.domain.models.Recipe

@Composable
fun RecipeDetailView(
    recipe: Recipe
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            RecipeImage(
                featuredImage = recipe.featuredImage
            )
        }
        item {
            RecipeTitleAndRating(
                title = recipe.title,
                rating = recipe.rating
            )
        }
        item {
            RecipeDateUpdatedAndPublisher(
                dateUpdated = recipe.dateUpdated,
                publisher = recipe.publisher
            )
        }
        recipe.ingredients?.whenNotNullOrEmpty { ingredients ->
            items(ingredients) { ingredient ->
                RecipeIngredientItem(ingredient = ingredient)
            }
        }
    }
}

@Composable
private fun RecipeImage(featuredImage: String?) {
    featuredImage?.let { url ->
        Image(
            painter = rememberImagePainter(
                data = url,
                builder = {
                    crossfade(true)
                    error(R.drawable.empty_plate)
                }
            ),
            contentDescription = stringResource(R.string.food_image),
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.recipe_card_height)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun RecipeTitleAndRating(title: String?, rating: Int?) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.recipe_detail_horizontal_padding)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        title?.whenNotNullOrBlank { title ->
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .wrapContentWidth(Alignment.Start),
                style = MaterialTheme.typography.h3
            )

        }
        rating?.let { rating ->
            Text(
                text = rating.toString(),
                modifier = Modifier
                    .wrapContentWidth(Alignment.End),
                style = MaterialTheme.typography.h5
            )
        }
    }
}

@Composable
private fun RecipeDateUpdatedAndPublisher(dateUpdated: String?, publisher: String?) {
    val dateUpdatedByPublisher = if (dateUpdated.isNullOrBlank()) {
        if (publisher.isNullOrBlank()) ""
        else stringResource(id = R.string.publisher, publisher)
    } else {
        if (publisher.isNullOrBlank())
            stringResource(id = R.string.date_updated, dateUpdated)
        else stringResource(
            id = R.string.date_updated_by_publisher,
            dateUpdated,
            publisher
        )
    }
    Text(
        text = dateUpdatedByPublisher,
        modifier = Modifier
            .wrapContentWidth(Alignment.Start)
            .padding(
                horizontal = dimensionResource(id = R.dimen.recipe_detail_horizontal_padding),
                vertical = 10.dp
            ),
        style = MaterialTheme.typography.caption
    )
}

@Composable
private fun RecipeIngredientItem(ingredient: String) {
    Text(
        text = ingredient,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.recipe_detail_horizontal_padding),
                vertical = 4.dp
            )
            .wrapContentWidth(Alignment.Start),
        style = MaterialTheme.typography.body1
    )
}