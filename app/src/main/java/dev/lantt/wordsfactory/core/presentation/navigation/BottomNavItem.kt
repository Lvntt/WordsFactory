package dev.lantt.wordsfactory.core.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavItem(
    @StringRes
    val labelId: Int,
    @DrawableRes
    val iconId: Int,
    val route: String
)