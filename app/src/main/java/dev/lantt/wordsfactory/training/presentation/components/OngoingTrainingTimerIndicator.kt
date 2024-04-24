package dev.lantt.wordsfactory.training.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import dev.lantt.wordsfactory.core.presentation.ui.theme.ErrorColor
import dev.lantt.wordsfactory.core.presentation.ui.theme.GradientEnd
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkGray
import dev.lantt.wordsfactory.core.presentation.ui.theme.PrimaryColor

@Composable
fun OngoingTrainingTimerIndicator() {
    var progress by remember { mutableFloatStateOf(1f) }

    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        // TODO 5000 to constants
        animationSpec = tween(5000, easing = LinearEasing),
        label = "progress_animation",
    )
    
    GradientLinearProgressIndicator(
        backgroundColor = InkGray,
        foregroundColor = Brush.linearGradient(
            colors = listOf(ErrorColor, PrimaryColor, GradientEnd)
        ),
        progress = progressAnimation,
        height = 5.dp
    )

    LaunchedEffect(Unit) {
        progress = 0f
    }
}