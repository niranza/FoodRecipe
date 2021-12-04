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
import com.niran.recipeapplication.core.common.extensions.whenNotNullOrBlank
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
            recipe.featuredImage?.whenNotNullOrBlank { url ->
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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                recipe.title?.whenNotNullOrBlank { title ->
                    Text(
                        text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h3
                    )
                }
                recipe.rating?.let { rating ->
                    Text(
                        text = rating.toString(),
                        modifier = Modifier
                            .wrapContentWidth(Alignment.End),
                        style = MaterialTheme.typography.h5
                    )
                }
            }
        }
    }
}