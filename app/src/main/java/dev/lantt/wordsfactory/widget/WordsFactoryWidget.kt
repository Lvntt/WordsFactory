package dev.lantt.wordsfactory.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.FontStyle
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkWhite
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingTiny
import dev.lantt.wordsfactory.core.presentation.ui.theme.PrimaryColor

class WordsFactoryWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Column(
                modifier = GlanceModifier
                    .width(329.dp)
                    .height(155.dp)
                    .cornerRadius(21.dp)
            ) {
                WidgetTitle(title = context.getString(R.string.appName))
                WidgetBody(context = context)
            }
        }
    }

    // TODO custom font
    @Composable
    fun WidgetTitle(title: String) {
        Box(
            modifier = GlanceModifier
                .fillMaxWidth()
                .background(PrimaryColor)
        ) {
            Text(
                modifier = GlanceModifier.padding(
                    horizontal = PaddingMedium,
                    vertical = PaddingTiny
                ),
                text = title,
                style = TextStyle(
                    color = ColorProvider(InkWhite),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
        }
    }

    @Composable
    fun WidgetBody(context: Context) {
        Column(
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium)
                .background(InkWhite)
        ) {
            Spacer(modifier = GlanceModifier.height(20.dp))

            WidgetBodyElement(
                primaryText = context.getString(R.string.myDictionary),
                secondaryText = context.getString(R.string.wordsAmount, 0)
            )

            Spacer(modifier = GlanceModifier.height(PaddingMedium))

            WidgetBodyElement(
                primaryText = context.getString(R.string.iAlreadyRemember),
                secondaryText = context.getString(R.string.wordsAmount, 0)
            )

            Spacer(modifier = GlanceModifier.height(PaddingMedium))
        }
    }

    @Composable
    fun WidgetBodyElement(
        primaryText: String,
        secondaryText: String
    ) {
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = primaryText,
                style = TextStyle(
                    color = ColorProvider(Color.Black),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal
                )
            )

            Spacer(modifier = GlanceModifier.defaultWeight())

            Text(
                text = secondaryText,
                style = TextStyle(
                    color = ColorProvider(Color(0xFF808080)),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal
                )
            )
        }
    }
}