package com.niran.recipeapplication.presentation.composables.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.niran.recipeapplication.R
import com.niran.recipeapplication.domain.models.Recipe

@Composable
fun ItemRecipe(
    recipe: Recipe,
    onclick: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(all = 2.dp)
            .fillMaxWidth()
            .clickable(onClick = onclick),
        elevation = 8.dp,
    ) {
        Column {
            recipe.featuredImage?.let { url ->
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
                        .height(225.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp),
            ) {
                Text(
                    text = recipe.title,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = recipe.rating?.toString() ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }
}