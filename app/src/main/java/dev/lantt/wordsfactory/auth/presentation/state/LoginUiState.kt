package dev.lantt.wordsfactory.auth.presentation.state

sealed interface LoginUiState {

    data object Initial : LoginUiState

    data object Loading : LoginUiState

    data object Success : LoginUiState

}