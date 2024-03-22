package dev.lantt.wordsfactory.auth.presentation.state

sealed interface ErrorDialogState {

    data object BadConnection : ErrorDialogState

    data object IncorrectInput : ErrorDialogState

}