package dev.lantt.wordsfactory.dictionary.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.theme.HeadingH4
import dev.lantt.wordsfactory.core.presentation.ui.theme.Padding5
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingSmall
import dev.lantt.wordsfactory.core.presentation.ui.theme.ParagraphMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PrimaryColor
import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.dictionary.presentation.viewmodel.DictionaryViewModel

@Composable
fun DictionaryWordContent(
    word: DictionaryWord,
    viewModel: DictionaryViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingMedium)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(PaddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = word.word,
                style = HeadingH4,
                color = Color.Black
            )

            word.phonetic?.let { phonetic ->
                Text(
                    modifier = Modifier.padding(top = PaddingSmall),
                    text = "[$phonetic]",
                    style = ParagraphMedium,
                    color = PrimaryColor
                )
            }

            word.phoneticAudio?.let { audio ->
                Icon(
                    modifier = Modifier
                        .padding(top = PaddingSmall)
                        .clickable {
                            viewModel.onPlayAudio(audio)
                        },
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_audio),
                    contentDescription = stringResource(id = R.string.listenPronunciation),
                    tint = PrimaryColor
                )
            }
        }

        Spacer(modifier = Modifier.height(PaddingMedium))

        Row(
            horizontalArrangement = Arrangement.spacedBy(PaddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.partsOfSpeech),
                style = HeadingH4,
                color = Color.Black
            )

            Text(
                modifier = Modifier.padding(top = Padding5),
                text = word.partsOfSpeech.joinToString(", "),
                style = ParagraphMedium,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(PaddingMedium))

        Text(
            text = stringResource(id = R.string.meanings),
            style = HeadingH4,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(11.dp))

        LazyColumn {
            items(word.meanings) { meaning ->
                WordMeaning(meaning = meaning)

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}