package dev.lantt.wordsfactory.training.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkGray
import dev.lantt.wordsfactory.core.presentation.ui.theme.PrimaryColor

@Composable
fun OngoingTrainingTimerIndicator(
    onFinishTraining: () -> Unit,
) {
    var progress by remember { mutableFloatStateOf(1f) }

    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(5000, easing = LinearEasing),
        label = "progress_animation",
        finishedListener = {
            // TODO next question
            onFinishTraining()
        }
    )

    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .height(5.dp),
        progress = { progressAnimation },
        color = PrimaryColor,
        trackColor = InkGray
    )

    LaunchedEffect(Unit) {
        progress = 0f
    }
}