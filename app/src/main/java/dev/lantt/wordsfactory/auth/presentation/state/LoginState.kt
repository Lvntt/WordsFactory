package dev.lantt.wordsfactory.auth.presentation.state

import androidx.annotation.StringRes

data class LoginState(
    val email: String = "lanttoff@gmail.com",
    val password: String = "123123",
    val isPasswordVisible: Boolean = false,
    val isErrorDialogShown: Boolean = false,
    @StringRes val errorTitleId: Int? = null,
    @StringRes val errorTextId: Int? = null
) {
    val isLoginEnabled =
        email.isNotBlank() &&
        password.isNotBlank()
}