package dev.lantt.wordsfactory.auth.presentation.state

data class RegistrationState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false
)
