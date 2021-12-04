package com.niran.recipeapplication.core.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.niran.recipeapplication.core.common.BUNDLE_ARGS
import com.niran.recipeapplication.core.common.extensions.whenNull

abstract class BaseFragment : Fragment() {

    override fun setArguments(args: Bundle?) {
        var navArgs = args
        navArgs?.let {
            it.getBundle(BUNDLE_ARGS).whenNull {
                navArgs = Bundle(args).apply { putBundle(BUNDLE_ARGS, args) }
            }
        }
        super.setArguments(navArgs)
    }
}