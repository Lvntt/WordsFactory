package dev.lantt.wordsfactory.training.presentation.event

sealed interface TrainingEvent {

    data object TrainingStarted : TrainingEvent

}