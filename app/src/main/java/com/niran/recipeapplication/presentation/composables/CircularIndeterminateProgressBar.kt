package com.niran.recipeapplication.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun CircularIndeterminateProgressBar() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize().padding(25.dp)
    ) {
        val progressBar = createRef()

        CircularProgressIndicator(
            modifier = Modifier.constrainAs(progressBar) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            color = MaterialTheme.colors.primary
        )
    }
}