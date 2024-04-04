package dev.lantt.wordsfactory.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.auth.domain.usecase.LoginUserUseCase
import dev.lantt.wordsfactory.auth.presentation.state.LoginState
import dev.lantt.wordsfactory.auth.presentation.state.LoginUiState
import dev.lantt.wordsfactory.core.domain.model.UserLoginDto
import dev.lantt.wordsfactory.core.domain.model.exception.InvalidCredentialsException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUserUseCase: LoginUserUseCase,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Initial)
    val loginUiState = _loginUiState.asStateFlow()

    private val loginExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val errorTitleId: Int
        val errorTextId: Int

        when (exception) {
            is InvalidCredentialsException -> {
                errorTitleId = R.string.invalidCredentials
                errorTextId = R.string.invalidCredentialsDescription
            }
            else -> {
                errorTitleId = R.string.connectionError
                errorTextId = R.string.connectionErrorDescription
            }
        }

        _loginUiState.update { LoginUiState.Initial }

        _loginState.update {
            it.copy(
                isErrorDialogShown = true,
                errorTitleId = errorTitleId,
                errorTextId = errorTextId
            )
        }
    }

    fun onEmailChange(email: String) {
        _loginState.update {
            it.copy(email = email)
        }
    }

    fun onPasswordChange(password: String) {
        _loginState.update {
            it.copy(password = password)
        }
    }

    fun onPasswordVisibilityChange() {
        _loginState.update {
            it.copy(isPasswordVisible = !it.isPasswordVisible)
        }
    }

    fun onLogin() {
        _loginUiState.update { LoginUiState.Loading }
        viewModelScope.launch(defaultDispatcher + loginExceptionHandler) {
            loginUserUseCase(
                user = UserLoginDto(
                    email = _loginState.value.email,
                    password = _loginState.value.password
                )
            )
            _loginUiState.update { LoginUiState.Success }
        }
    }

    fun onDismissErrorDialog() {
        _loginState.update {
            it.copy(
                isErrorDialogShown = false,
                errorTitleId = null,
                errorTextId = null
            )
        }
    }

}