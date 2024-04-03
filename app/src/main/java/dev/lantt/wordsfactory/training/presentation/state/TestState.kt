package dev.lantt.wordsfactory.training.presentation.state

sealed interface TestState {

    data object Ongoing : TestState

    data object Finished : TestState

}