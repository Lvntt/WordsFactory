package dev.lantt.wordsfactory.auth.presentation.state

import androidx.annotation.StringRes

data class RegistrationState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isErrorDialogShown: Boolean = false,
    @StringRes val errorTitleId: Int? = null,
    @StringRes val errorTextId: Int? = null
) {
    val isRegistrationEnabled =
        name.isNotBlank() &&
        email.isNotBlank() &&
        password.length >= MIN_PASSWORD_LENGTH

    private companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }
}
