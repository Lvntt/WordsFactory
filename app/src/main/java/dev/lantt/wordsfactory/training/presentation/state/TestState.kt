package dev.lantt.wordsfactory.training.presentation.state

sealed interface TestState {

    data object Loading : TestState

    data class Ongoing(val question: QuestionState) : TestState

    data class Finished(val testStatistics: TestStatistics) : TestState

}