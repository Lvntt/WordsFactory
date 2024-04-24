package dev.lantt.wordsfactory.widget.presentation.util

import androidx.annotation.FontRes
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class GlanceTextStyle(
    @FontRes val font: Int,
    val fontSize: TextUnit,
    val letterSpacing: TextUnit = 0.sp
)
