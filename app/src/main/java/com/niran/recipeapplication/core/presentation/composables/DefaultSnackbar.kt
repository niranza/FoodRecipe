package com.niran.recipeapplication.core.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
) {
    SnackbarHost(
        modifier = modifier,
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = modifier.padding(16.dp),
                content = {
                    Text(
                        text = data.message,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface,
                    )
                },
                action = {
                    data.actionLabel?.let { label ->
                        TextButton(onClick = { onDismiss() }) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.body2,
                                color = MaterialTheme.colors.onSurface,
                            )
                        }
                    }
                },
            )
        },
    )
}