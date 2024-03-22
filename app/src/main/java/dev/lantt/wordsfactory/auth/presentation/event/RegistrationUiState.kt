package dev.lantt.wordsfactory.auth.presentation.event

sealed interface RegistrationUiState {

    data object Initial : RegistrationUiState

    data object Loading : RegistrationUiState

    data object Success : RegistrationUiState

}