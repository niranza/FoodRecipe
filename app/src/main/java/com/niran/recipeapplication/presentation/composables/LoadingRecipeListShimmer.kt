package com.niran.recipeapplication.presentation.composables

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingRecipeListShimmer(
    cardHeight: Dp,
    padding: Dp = 6.dp,
) {

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val cardWidthPx = with(LocalDensity.current) { (maxWidth - (padding * 2)).toPx() }
        val cardHeightPx = with(LocalDensity.current) { (cardHeight - (padding * 2)).toPx() }
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

        LazyColumn(
            contentPadding = PaddingValues(padding)
        ) {
            items(3) {
                ItemRecipeShimmer(
                    colors = colors,
                    xShimmer = xCardShimmer.value,
                    yShimmer = yCardShimmer.value,
                    cardHeight = cardHeight,
                    gradientWidth = gradientWidth
                )
            }
        }
    }
}

@Composable
private fun ItemRecipeShimmer(
    colors: List<Color>,
    xShimmer: Float,
    yShimmer: Float,
    cardHeight: Dp,
    gradientWidth: Float,
    padding: Dp = 4.dp,
) {
    val brush = Brush.linearGradient(
        colors = colors,
        start = Offset(xShimmer - gradientWidth, yShimmer - gradientWidth),
        end = Offset(xShimmer, yShimmer)
    )
    Column(
        modifier = Modifier
            .padding(padding)
    ) {
        Surface(
            shape = MaterialTheme.shapes.small
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight)
                    .background(brush = brush),
            )
        }
        Spacer(
            modifier = Modifier.height(4.dp)
        )
        Surface(
            shape = MaterialTheme.shapes.small
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight / 4f)
                    .background(brush = brush),
            )
        }
    }
}