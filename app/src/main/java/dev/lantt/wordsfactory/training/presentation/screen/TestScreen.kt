package dev.lantt.wordsfactory.training.presentation.screen

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.lantt.wordsfactory.training.presentation.components.FinishTrainingScreen
import dev.lantt.wordsfactory.training.presentation.components.TestQuestionScreen
import dev.lantt.wordsfactory.training.presentation.state.TestState
import dev.lantt.wordsfactory.training.presentation.viewmodel.TestViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TestScreen(
    modifier: Modifier = Modifier,
    viewModel: TestViewModel = koinViewModel()
) {
    val testState by viewModel.testState.collectAsStateWithLifecycle()

    Crossfade(
        targetState = testState,
        label = "test_crossfade"
    ) { state ->
        when (state) {
            TestState.Loading -> {
                // TODO
            }
            is TestState.Ongoing -> TestQuestionScreen(
                question =
                modifier = modifier,
                onFinishTraining = viewModel::onFinishTraining
            )
            TestState.Finished -> FinishTrainingScreen(
                modifier = modifier,
                onTrainAgain = viewModel::onTrainAgain
            )
        }
    }

}

@Preview
@Composable
private fun TestScreenPreview() {
    TestScreen()
}