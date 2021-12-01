package com.niran.recipeapplication.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.niran.recipeapplication.R
import com.niran.recipeapplication.domain.enums.FoodCategory
import com.niran.recipeapplication.presentation.composables.items.ItemFoodCategory

@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: KeyboardActionScope.() -> Unit,
    categoryScrollState: LazyListState,
    isSelected: (FoodCategory) -> Boolean,
    onSelectedCategoryChanged: (FoodCategory) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp,
    ) {

        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = query,
                    onValueChange = onQueryChanged,
                    label = {
                        Text(
                            text = stringResource(id = R.string.search),
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = stringResource(id = R.string.search)
                        )
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = onSearch
                    ),
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onSurface,
                    ),
                )
            }
            LazyRow(
                state = categoryScrollState
            ) {
                itemsIndexed(
                    items = FoodCategory.values()
                ) { index, foodCategory ->
                    ItemFoodCategory(
                        category = foodCategory,
                        isSelected = isSelected(foodCategory),
                        onSelectedCategoryChange = onSelectedCategoryChanged
                    )
                }
            }
        }
    }
}