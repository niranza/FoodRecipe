package com.niran.recipeapplication.presentation.composables

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.niran.recipeapplication.R
import kotlin.random.Random

@Composable
fun LoadingRecipeDetailShimmer(
    padding: Dp = 0.dp,
) {
    val randomFraction = remember { mutableStateOf(listOf<Float>()) }

    LaunchedEffect(true) {
        randomFraction.value = createRandomFloatList(0.4f, 1f, 10)
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val cardWidthPx = with(LocalDensity.current) { (maxWidth - (padding * 2)).toPx() }
        val cardHeightPx =
            with(LocalDensity.current) { (dimensionResource(id = R.dimen.recipe_card_height) - (padding * 2)).toPx() }
        val gradientWidth = 2.5f * cardHeightPx

        val durationInMillis = 1000

        val infiniteTransition = rememberInfiniteTransition()

        val xCardShimmer = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = cardWidthPx + gradientWidth,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationInMillis,
                    easing = LinearEasing,
                ),
                repeatMode = RepeatMode.Restart
            )
        )

        val yCardShimmer = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = cardHeightPx + gradientWidth,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationInMillis,
                    easing = LinearEasing,
                ),
                repeatMode = RepeatMode.Restart
            )
        )

        val colors = listOf(
            Color.Gray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.6f),
            Color.Gray.copy(alpha = 0.6f),
        )

        val brush = Brush.linearGradient(
            colors = colors,
            start = Offset(xCardShimmer.value - gradientWidth, yCardShimmer.value - gradientWidth),
            end = Offset(xCardShimmer.value, yCardShimmer.value)
        )

        LazyColumn(
            contentPadding = PaddingValues(padding)
        ) {
            item {
                Surface(
                    shape = MaterialTheme.shapes.small
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.recipe_card_height))
                            .background(brush = brush),
                    )
                }
            }
            item {
                Surface(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.recipe_detail_horizontal_padding),
                        vertical = 10.dp
                    ),
                    shape = MaterialTheme.shapes.small
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(brush = brush),
                    )
                }
            }
            item {
                Surface(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.recipe_detail_horizontal_padding),
                        vertical = 8.dp
                    ),
                    shape = MaterialTheme.shapes.small
                ) {
                    Spacer(
                        modifier = Modifier
                            .width(250.dp)
                            .height(15.dp)
                            .background(brush = brush),
                    )
                }
            }
            items(10) { index ->
                Surface(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.recipe_detail_horizontal_padding),
                        vertical = 4.dp
                    ),
                    shape = MaterialTheme.shapes.small
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth(if (randomFraction.value.isNotEmpty()) randomFraction.value[index] else 0f)
                            .height(25.dp)
                            .background(brush = brush),
                    )
                }
            }
        }
    }
}

private fun createRandomFloatList(min: Float, max: Float, iterations: Int): List<Float> {
    val result = mutableListOf<Float>()
    repeat(iterations) {
        result.add(min + (Random.nextFloat() * (max - min)))
    }
    return result
}