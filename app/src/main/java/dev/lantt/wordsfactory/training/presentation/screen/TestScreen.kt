package dev.lantt.wordsfactory.training.presentation.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.lantt.wordsfactory.dictionary.presentation.components.LottieLoading
import dev.lantt.wordsfactory.training.presentation.components.FinishTrainingScreen
import dev.lantt.wordsfactory.training.presentation.components.TestQuestionScreen
import dev.lantt.wordsfactory.training.presentation.state.TestState
import dev.lantt.wordsfactory.training.presentation.viewmodel.TestViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TestScreen(
    onBack: () -> Unit,
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
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LottieLoading(
                        modifier = Modifier
                            .size(100.dp)
                            .fillMaxWidth()
                    )
                }
            }
            is TestState.Ongoing -> TestQuestionScreen(
                question = state.question,
                viewModel = viewModel,
                modifier = modifier
            )
            is TestState.Finished -> FinishTrainingScreen(
                testStatistics = state.testStatistics,
                onTrainAgain = viewModel::onTrainAgain,
                onBack = onBack,
                modifier = modifier
            )
        }
    }

}

@Preview
@Composable
private fun TestScreenPreview() {
    TestScreen({})
}