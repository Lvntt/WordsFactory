package dev.lantt.wordsfactory.training.presentation.state

import dev.lantt.wordsfactory.training.domain.entity.Question

sealed interface TestState {

    data object Loading : TestState

    data class Ongoing(val currentQuestion: Question) : TestState

    data object Finished : TestState

}