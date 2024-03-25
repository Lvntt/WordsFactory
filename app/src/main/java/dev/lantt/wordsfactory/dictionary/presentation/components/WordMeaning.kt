package dev.lantt.wordsfactory.dictionary.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.theme.CornerRadiusMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkGray
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingSmall
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingTiny
import dev.lantt.wordsfactory.core.presentation.ui.theme.ParagraphMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.SecondaryColor
import dev.lantt.wordsfactory.dictionary.data.model.MeaningDto

@Composable
fun WordMeaning(
    meaning: MeaningDto,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(CornerRadiusMedium))
            .border(
                width = 1.dp,
                color = InkGray,
                shape = RoundedCornerShape(CornerRadiusMedium)
            )
    ) {
        Column(
            modifier = Modifier.padding(PaddingMedium),
            verticalArrangement = Arrangement.spacedBy(PaddingSmall)
        ) {
            Text(
                text = meaning.definitions[0].definition,
                style = ParagraphMedium,
                color = Color.Black
            )

            // TODO spannable
            val example = meaning.definitions[0].example
            example?.let {
                Row {
                    Text(
                        text = stringResource(id = R.string.example),
                        style = ParagraphMedium,
                        color = SecondaryColor
                    )

                    Spacer(modifier = Modifier.width(PaddingTiny))

                    Text(
                        text = example,
                        style = ParagraphMedium,
                        color = Color.Black
                    )
                }
            }
        }
    }
}