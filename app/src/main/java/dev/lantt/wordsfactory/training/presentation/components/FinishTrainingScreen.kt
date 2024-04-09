package dev.lantt.wordsfactory.training.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.shared.PrimaryButton
import dev.lantt.wordsfactory.core.presentation.ui.theme.HeadingH4
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkDark
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkDarkGray
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingLarge
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingRegular
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingSmall
import dev.lantt.wordsfactory.core.presentation.ui.theme.ParagraphMedium
import dev.lantt.wordsfactory.training.presentation.state.TestStatistics

@Composable
fun FinishTrainingScreen(
    testStatistics: TestStatistics,
    onTrainAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = PaddingRegular),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painterResource(id = R.drawable.img_finish_training),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(PaddingLarge))

        Text(
            text = stringResource(id = R.string.trainingFinished),
            style = HeadingH4,
            color = InkDark
        )

        Spacer(modifier = Modifier.height(PaddingSmall))

        Text(
            text = stringResource(id = R.string.correctWords, testStatistics.correctWords),
            style = ParagraphMedium,
            color = InkDarkGray
        )

        Text(
            text = stringResource(id = R.string.incorrectWords, testStatistics.incorrectWords),
            style = ParagraphMedium,
            color = InkDarkGray
        )

        Spacer(modifier = Modifier.height(PaddingLarge))

        PrimaryButton(
            onClick = onTrainAgain,
            text = stringResource(id = R.string.again)
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}