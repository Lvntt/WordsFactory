package dev.lantt.wordsfactory.training.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun GradientLinearProgressIndicator(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    foregroundColor: Brush,
    progress: Float,
    height: Dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .height(height)
    ) {
        Box(
            modifier = modifier
                .background(foregroundColor)
                .fillMaxWidth(progress)
                .height(height)
        )
    }
}