package dev.lantt.wordsfactory.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.core.domain.model.UserRegisterDto
import dev.lantt.wordsfactory.auth.domain.usecase.RegisterUserUseCase
import dev.lantt.wordsfactory.auth.presentation.event.RegistrationUiState
import dev.lantt.wordsfactory.auth.presentation.state.ErrorDialogState
import dev.lantt.wordsfactory.auth.presentation.state.RegistrationState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _errorDialogState = MutableSharedFlow<ErrorDialogState>()
    val errorDialogState = _errorDialogState.asSharedFlow()

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
        viewModelScope.launch(defaultDispatcher) {
            registerUserUseCase(
                user = UserRegisterDto(
                    name = _registrationState.value.name,
                    email = _registrationState.value.email,
                    password = _registrationState.value.password
                )
            )
            _registrationUiState.update { RegistrationUiState.Success }
            // _errorDialogState.emit(ErrorDialogState.IncorrectInput)
        }
    }

    fun resetState() {
        _registrationUiState.update { RegistrationUiState.Initial }
    }

}