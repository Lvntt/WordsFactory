package dev.lantt.wordsfactory.auth.presentation.state

import androidx.annotation.StringRes

data class LoginState(
    val email: String,
    val password: String,
    val isPasswordVisible: Boolean = false,
    val isErrorDialogShown: Boolean = false,
    @StringRes val errorTitleId: Int? = null,
    @StringRes val errorTextId: Int? = null
) {
    val isLoginEnabled =
        email.isNotBlank() &&
        password.isNotBlank()
}