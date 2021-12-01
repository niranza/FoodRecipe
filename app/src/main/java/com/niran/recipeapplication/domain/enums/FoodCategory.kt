package com.niran.recipeapplication.domain.enums

import androidx.annotation.StringRes
import com.niran.recipeapplication.R

enum class FoodCategory(@StringRes val nameId: Int) {
    CHICKEN(R.string.chicken),
    BEEF(R.string.beef),
    SOUP(R.string.soup),
    DESSERT(R.string.dessert),
    VEGETARIAN(R.string.vegetarian),
    MILK(R.string.milk),
    VEGAN(R.string.vegan),
    PIZZA(R.string.pizza),
    DONUT(R.string.donut);


}