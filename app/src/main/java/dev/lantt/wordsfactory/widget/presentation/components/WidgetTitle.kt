package dev.lantt.wordsfactory.widget.presentation.components

import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import dev.lantt.wordsfactory.core.presentation.ui.theme.GradientEnd
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkWhite
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingTiny
import dev.lantt.wordsfactory.core.presentation.ui.theme.PrimaryColor
import dev.lantt.wordsfactory.widget.presentation.style.GlanceHeadingH3
import dev.lantt.wordsfactory.widget.presentation.util.linearGradientBackground

@Composable
fun WidgetTitle(title: String) {
    Box(
        modifier = GlanceModifier
            .fillMaxWidth()
            .linearGradientBackground(PrimaryColor, GradientEnd)
    ) {
        GlanceText(
            modifier = GlanceModifier.padding(
                horizontal = PaddingMedium,
                vertical = PaddingTiny
            ),
            text = title,
            color = InkWhite,
            style = GlanceHeadingH3
        )
    }
}