package com.molog.knots.models

import androidx.annotation.DrawableRes
import com.molog.knots.navigation.Routes

data class MyBottomNavItem(
    val title: String,
    val screen: Routes,
    @DrawableRes val icon: Int,
)
