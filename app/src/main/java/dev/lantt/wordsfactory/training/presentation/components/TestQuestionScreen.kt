package dev.lantt.wordsfactory.training.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.theme.HeadingH4
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkDark
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkDarkGray
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingRegular
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingSmall
import dev.lantt.wordsfactory.core.presentation.ui.theme.ParagraphLarge
import dev.lantt.wordsfactory.training.presentation.state.QuestionState
import dev.lantt.wordsfactory.training.presentation.viewmodel.TestViewModel

@Composable
fun TestQuestionScreen(
    question: QuestionState,
    viewModel: TestViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(PaddingMedium)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(PaddingMedium))

            Text(
                text = stringResource(id = R.string.wordsProgress, question.number, question.total),
                style = ParagraphLarge,
                color = InkDarkGray
            )

            Spacer(modifier = Modifier.height(PaddingSmall))

            Text(
                modifier = Modifier.heightIn(max = 300.dp),
                text = question.correctWordDefinition,
                style = HeadingH4,
                color = InkDark,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(PaddingRegular))

            TestOption(
                optionOrder = stringResource(id = R.string.aOption),
                optionText = question.currentQuestion.options[0],
                onChooseOption = {
                    viewModel.onChooseOption(question.currentQuestion, it)
                },
                isSelected = question.selectedWord == question.currentQuestion.options[0]
            )

            Spacer(modifier = Modifier.height(PaddingMedium))

            TestOption(
                optionOrder = stringResource(id = R.string.bOption),
                optionText = question.currentQuestion.options[1],
                onChooseOption = {
                    viewModel.onChooseOption(question.currentQuestion, it)
                },
                isSelected = question.selectedWord == question.currentQuestion.options[1]
            )

            Spacer(modifier = Modifier.height(PaddingMedium))

            TestOption(
                optionOrder = stringResource(id = R.string.cOption),
                optionText = question.currentQuestion.options[2],
                onChooseOption = {
                    viewModel.onChooseOption(question.currentQuestion, it)
                },
                isSelected = question.selectedWord == question.currentQuestion.options[2]
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        OngoingTrainingTimerIndicator()
    }
}