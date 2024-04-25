package dev.lantt.wordsfactory.widget.presentation.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkWhite
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.widget.domain.usecase.GetDictionaryWordsCountUseCase
import dev.lantt.wordsfactory.widget.domain.usecase.GetLearntDictionaryWordsCountUseCase
import dev.lantt.wordsfactory.widget.presentation.components.WidgetBodyElement
import dev.lantt.wordsfactory.widget.presentation.components.WidgetTitle
import org.koin.java.KoinJavaComponent.inject

class WordsFactoryWidget : GlanceAppWidget() {

    private val getDictionaryWordsCountUseCase: GetDictionaryWordsCountUseCase by inject(GetDictionaryWordsCountUseCase::class.java)
    private val getLearntDictionaryWordsCountUseCase: GetLearntDictionaryWordsCountUseCase by inject(GetLearntDictionaryWordsCountUseCase::class.java)

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val totalWords by getDictionaryWordsCountUseCase().collectAsState(initial = 0)
            val learntWords by getLearntDictionaryWordsCountUseCase().collectAsState(initial = 0)

            Column(
                modifier = GlanceModifier
                    .width(329.dp)
                    .cornerRadius(21.dp)
            ) {
                WidgetTitle(title = context.getString(R.string.appName))
                WidgetBody(
                    context = context,
                    totalWords = totalWords,
                    learntWords = learntWords
                )
            }
        }
    }

    @Composable
    fun WidgetBody(
        context: Context,
        totalWords: Int,
        learntWords: Int
    ) {
        Column(
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium)
                .background(InkWhite)
        ) {
            Spacer(modifier = GlanceModifier.height(20.dp))

            WidgetBodyElement(
                primaryText = context.getString(R.string.myDictionary),
                secondaryText = context.getString(R.string.wordsAmount, totalWords)
            )

            Spacer(modifier = GlanceModifier.height(PaddingMedium))

            WidgetBodyElement(
                primaryText = context.getString(R.string.iAlreadyRemember),
                secondaryText = context.getString(R.string.wordsAmount, learntWords)
            )

            Spacer(modifier = GlanceModifier.height(PaddingMedium))
        }
    }
}