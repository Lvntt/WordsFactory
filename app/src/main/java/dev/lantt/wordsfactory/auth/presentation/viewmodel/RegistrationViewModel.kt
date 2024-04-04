package dev.lantt.wordsfactory.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.domain.model.UserRegisterDto
import dev.lantt.wordsfactory.auth.domain.usecase.RegisterUserUseCase
import dev.lantt.wordsfactory.auth.presentation.event.RegistrationUiState
import dev.lantt.wordsfactory.auth.presentation.state.RegistrationState
import dev.lantt.wordsfactory.core.domain.model.exception.InvalidCredentialsException
import dev.lantt.wordsfactory.core.domain.model.exception.UserCollisionException
import dev.lantt.wordsfactory.core.domain.model.exception.WeakPasswordException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val defaultDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _registrationState = MutableStateFlow(RegistrationState())
    val registrationState = _registrationState.asStateFlow()

    private val _registrationUiState = MutableStateFlow<RegistrationUiState>(RegistrationUiState.Initial)
    val registrationUiState = _registrationUiState.asStateFlow()

    private val registrationExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val errorTitleId: Int
        val errorTextId: Int

        when (exception) {
            is WeakPasswordException,
            is UserCollisionException,
            is InvalidCredentialsException -> {
                errorTitleId = R.string.invalidInput
                errorTextId = R.string.invalidInputDescription
            }
            else -> {
                errorTitleId = R.string.connectionError
                errorTextId = R.string.connectionErrorDescription
            }
        }

        _registrationUiState.update { RegistrationUiState.Initial }

        _registrationState.update {
            it.copy(
                isErrorDialogShown = true,
                errorTitleId = errorTitleId,
                errorTextId = errorTextId
            )
        }
    }

    fun onNameChange(name: String) {
        _registrationState.update {
            it.copy(name = name)
        }
    }

    fun onEmailChange(email: String) {
        _registrationState.update {
            it.copy(email = email)
        }
    }

    fun onPasswordChange(password: String) {
        _registrationState.update {
            it.copy(password = password)
        }
    }

    fun onPasswordVisibilityChange() {
        _registrationState.update {
            it.copy(isPasswordVisible = !it.isPasswordVisible)
        }
    }

    fun onRegister() {
        _registrationUiState.update { RegistrationUiState.Loading }
        viewModelScope.launch(defaultDispatcher + registrationExceptionHandler) {
            registerUserUseCase(
                user = UserRegisterDto(
                    name = _registrationState.value.name,
                    email = _registrationState.value.email,
                    password = _registrationState.value.password
                )
            )
            _registrationUiState.update { RegistrationUiState.Success }
        }
    }

    fun onDismissErrorDialog() {
        _registrationState.update {
            it.copy(
                isErrorDialogShown = false,
                errorTitleId = null,
                errorTextId = null
            )
        }
    }

}