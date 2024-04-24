package dev.lantt.wordsfactory.widget.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.glance.GlanceModifier
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import dev.lantt.wordsfactory.widget.presentation.style.GlanceHeadingH5
import dev.lantt.wordsfactory.widget.presentation.style.GlanceParagraphMedium

@Composable
fun WidgetBodyElement(
    primaryText: String,
    secondaryText: String
) {
    Row(
        modifier = GlanceModifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        GlanceText(
            text = primaryText,
            style = GlanceHeadingH5,
            color = Color.Black
        )

        Spacer(modifier = GlanceModifier.defaultWeight())

        GlanceText(
            text = secondaryText,
            style = GlanceParagraphMedium,
            color = Color(0xFF808080)
        )
    }
}