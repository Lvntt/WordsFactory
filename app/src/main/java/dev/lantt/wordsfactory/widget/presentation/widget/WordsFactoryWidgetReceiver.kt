package dev.lantt.wordsfactory.widget.presentation.widget

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class WordsFactoryWidgetReceiver(
    override val glanceAppWidget: GlanceAppWidget = WordsFactoryWidget()
) : GlanceAppWidgetReceiver()