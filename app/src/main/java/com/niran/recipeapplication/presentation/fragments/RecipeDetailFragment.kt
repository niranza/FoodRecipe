package com.niran.recipeapplication.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.niran.recipeapplication.presentation.theme.RecipeApplicationTheme

class RecipeDetailFragment : Fragment() {

    private val navArgs: RecipeDetailFragmentArgs by navArgs()
    private val recipeId get() = navArgs.recipeId

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            RecipeApplicationTheme {
                Surface {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "Recipe Id is: $recipeId",
                            style = MaterialTheme.typography.h5
                        )
                    }
                }
            }
        }
    }
}