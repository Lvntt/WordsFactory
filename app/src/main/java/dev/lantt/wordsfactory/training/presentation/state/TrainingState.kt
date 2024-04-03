package dev.lantt.wordsfactory.training.presentation.state

sealed interface TrainingState {

    data object Idle : TrainingState

    data object TimerCountdown : TrainingState

}