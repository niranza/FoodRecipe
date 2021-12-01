package com.niran.recipeapplication.presentation.composables.items

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.niran.recipeapplication.domain.enums.FoodCategory

@Composable
fun ItemFoodCategory(
    category: FoodCategory,
    isSelected: Boolean,
    onSelectedCategoryChange: (FoodCategory) -> Unit,
) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .toggleable(value = isSelected) {
                onSelectedCategoryChange(category)
            },
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) Color.Gray else MaterialTheme.colors.primary
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(id = category.nameId),
                style = MaterialTheme.typography.body2,
                color = Color.White
            )
        }
    }
}