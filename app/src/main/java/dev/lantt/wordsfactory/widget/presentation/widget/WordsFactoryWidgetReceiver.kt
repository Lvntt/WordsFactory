package dev.lantt.wordsfactory.widget.presentation.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.updateAll
import kotlinx.coroutines.runBlocking

class WordsFactoryWidgetReceiver(
    override val glanceAppWidget: GlanceAppWidget = WordsFactoryWidget()
) : GlanceAppWidgetReceiver() {
    override fun onEnabled(context: Context?) {
        super.onEnabled(context)

        if (context != null) {
            runBlocking {
                WordsFactoryWidget().updateAll(context)
            }
        }
    }
}