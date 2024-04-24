package dev.lantt.wordsfactory.widget.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import dev.lantt.wordsfactory.widget.presentation.util.GlanceTextStyle
import dev.lantt.wordsfactory.widget.presentation.util.textAsBitmap

@Composable
fun GlanceText(
    modifier: GlanceModifier = GlanceModifier,
    style: GlanceTextStyle,
    text: String,
    color: Color = Color.Black,
) {
    Image(
        modifier = modifier,
        provider = ImageProvider(
            LocalContext.current.textAsBitmap(
                text = text,
                color = color,
                fontSize = style.fontSize,
                font = style.font,
                letterSpacing = style.letterSpacing.value
            )
        ),
        contentDescription = null
    )
}