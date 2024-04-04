package dev.lantt.wordsfactory.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.auth.presentation.viewmodel.RegistrationViewModel
import dev.lantt.wordsfactory.core.presentation.ui.shared.InputTextField
import dev.lantt.wordsfactory.core.presentation.ui.shared.InputTextFieldWithAction
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium

@Composable
fun RegistrationForm(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel
) {
    val state by viewModel.registrationState.collectAsStateWithLifecycle()

    if (state.isErrorDialogShown) {
        val errorTitleId = state.errorTitleId
        val errorTextId = state.errorTextId
        
        if (errorTitleId != null && errorTextId != null) {
            AuthErrorDialog(
                onDismiss = viewModel::onDismissErrorDialog,
                errorTitle = stringResource(id = errorTitleId),
                errorText = stringResource(id = errorTextId)
            )
        }        
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        InputTextField(
            value = state.name,
            onValueChange = viewModel::onNameChange,
            placeholder = stringResource(id = R.string.name)
        )

        InputTextField(
            value = state.email,
            onValueChange = viewModel::onEmailChange,
            placeholder = stringResource(id = R.string.email)
        )

        InputTextFieldWithAction(
            value = state.password,
            onValueChange = viewModel::onPasswordChange,
            placeholder = stringResource(id = R.string.password),
            trailingIcon = ImageVector.vectorResource(
                id = if (state.isPasswordVisible) R.drawable.ic_password_hide
                else R.drawable.ic_password_show
            ),
            trailingIconDescription = stringResource(id = R.string.changePasswordVisibility),
            onTrailingIconClick = viewModel::onPasswordVisibilityChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (state.isPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation()
        )
    }
}