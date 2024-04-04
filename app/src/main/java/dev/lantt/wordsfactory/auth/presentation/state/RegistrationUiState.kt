package dev.lantt.wordsfactory.auth.presentation.state

sealed interface RegistrationUiState {

    data object Initial : RegistrationUiState

    data object Loading : RegistrationUiState

    data object Success : RegistrationUiState

}