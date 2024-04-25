package dev.lantt.wordsfactory.training.presentation.components

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.lantt.wordsfactory.core.presentation.ui.theme.ErrorColor
import dev.lantt.wordsfactory.core.presentation.ui.theme.HeadingH1
import dev.lantt.wordsfactory.core.presentation.ui.theme.PrimaryColor
import dev.lantt.wordsfactory.core.presentation.ui.theme.SecondaryColor
import dev.lantt.wordsfactory.core.presentation.ui.theme.SuccessColor
import dev.lantt.wordsfactory.core.presentation.ui.theme.WarningColor
import dev.lantt.wordsfactory.training.presentation.state.TimerIndicatorState
import kotlinx.coroutines.delay

@Composable
fun StartTrainingTimerIndicator(
    onEndTimer: () -> Unit,
    modifier: Modifier = Modifier
) {
    var timerIndicatorState by remember { mutableStateOf<TimerIndicatorState>(TimerIndicatorState.FiveSeconds) }
    var progress by remember { mutableFloatStateOf(0f) }

    val timerStates = listOf(
        TimerIndicatorState.FourSeconds,
        TimerIndicatorState.ThreeSeconds,
        TimerIndicatorState.TwoSeconds,
        TimerIndicatorState.OneSecond,
        TimerIndicatorState.GoState
    )

    LaunchedEffect(Unit) {
        for (state in timerStates) {
            progress = 0f
            animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(1000)
            ) { value, _ ->
                progress = value
            }
            timerIndicatorState = state
        }
        delay(1000)
        onEndTimer()
    }

    val timerIndicatorColor = when (timerIndicatorState) {
        TimerIndicatorState.FiveSeconds -> PrimaryColor
        TimerIndicatorState.FourSeconds -> SecondaryColor
        TimerIndicatorState.ThreeSeconds -> SuccessColor
        TimerIndicatorState.TwoSeconds -> WarningColor
        TimerIndicatorState.OneSecond -> ErrorColor
        TimerIndicatorState.GoState -> PrimaryColor
    }

    val timerTrackColor = when (timerIndicatorState) {
        TimerIndicatorState.FiveSeconds -> Color.Black
        TimerIndicatorState.FourSeconds -> PrimaryColor
        TimerIndicatorState.ThreeSeconds -> SecondaryColor
        TimerIndicatorState.TwoSeconds -> SuccessColor
        TimerIndicatorState.OneSecond -> WarningColor
        TimerIndicatorState.GoState -> ErrorColor
    }

    val timerText = when (timerIndicatorState) {
        TimerIndicatorState.FiveSeconds -> "5"
        TimerIndicatorState.FourSeconds -> "4"
        TimerIndicatorState.ThreeSeconds -> "3"
        TimerIndicatorState.TwoSeconds -> "2"
        TimerIndicatorState.OneSecond -> "1"
        TimerIndicatorState.GoState -> "GO!"
    }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        if (timerIndicatorState != TimerIndicatorState.GoState) {
            CircularProgressIndicator(
                progress = { progress },
                modifier = Modifier.size(140.dp),
                color = timerIndicatorColor,
                strokeWidth = 7.dp,
                trackColor = timerTrackColor,
                strokeCap = StrokeCap.Round
            )
        }

        Text(
            text = timerText,
            style = HeadingH1,
            color = Color.Black
        )
    }
}

@Preview
@Composable
private fun StartTrainingTimerIndicatorPreview() {
    StartTrainingTimerIndicator({})
}