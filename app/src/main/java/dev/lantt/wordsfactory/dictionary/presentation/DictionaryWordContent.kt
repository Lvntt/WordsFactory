package dev.lantt.wordsfactory.dictionary.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.theme.CornerRadiusMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.HeadingH4
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkGray
import dev.lantt.wordsfactory.core.presentation.ui.theme.Padding5
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingSmall
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingTiny
import dev.lantt.wordsfactory.core.presentation.ui.theme.ParagraphMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PrimaryColor
import dev.lantt.wordsfactory.core.presentation.ui.theme.SecondaryColor

@Composable
fun DictionaryWordContent(
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
                text = "Cooking",
                style = HeadingH4,
                color = Color.Black
            )

            Text(
                modifier = Modifier.padding(top = PaddingSmall),
                text = "[ˈkʊkɪŋ]",
                style = ParagraphMedium,
                color = PrimaryColor
            )

            Icon(
                modifier = Modifier
                    .padding(top = PaddingSmall)
                    .clickable {
                        // TODO
                    },
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_audio),
                contentDescription = stringResource(id = R.string.listenPronunciation),
                tint = PrimaryColor
            )
        }

        Spacer(modifier = Modifier.height(PaddingMedium))

        Row(
            horizontalArrangement = Arrangement.spacedBy(PaddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.partOfSpeech),
                style = HeadingH4,
                color = Color.Black
            )

            Text(
                modifier = Modifier.padding(top = Padding5),
                text = "Noun",
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

        Box(
            modifier = Modifier
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
                    text = "The practice or skill of preparing food by combining, mixing, and heating ingredients.",
                    style = ParagraphMedium,
                    color = Color.Black
                )

                Row {
                    Text(
                        text = stringResource(id = R.string.example),
                        style = ParagraphMedium,
                        color = SecondaryColor
                    )

                    Spacer(modifier = Modifier.width(PaddingTiny))

                    Text(
                        text = "he developed an interest in cooking.",
                        style = ParagraphMedium,
                        color = Color.Black
                    )
                }
            }
        }
    }
}