package dev.lantt.wordsfactory.auth.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.auth.presentation.components.LoginForm
import dev.lantt.wordsfactory.auth.presentation.state.LoginUiState
import dev.lantt.wordsfactory.auth.presentation.viewmodel.LoginViewModel
import dev.lantt.wordsfactory.core.presentation.ui.shared.LoadingPrimaryButton
import dev.lantt.wordsfactory.core.presentation.ui.shared.PrimaryButton
import dev.lantt.wordsfactory.core.presentation.ui.theme.HeadingH4
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingRegular
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingTiny
import dev.lantt.wordsfactory.core.presentation.ui.theme.ParagraphMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.SecondaryColor
import dev.lantt.wordsfactory.core.presentation.util.noRippleClickable
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    onNavigateToRegistration: () -> Unit,
    onNavigateToDictionary: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel()
) {
    val focusManager = LocalFocusManager.current
    val uiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    if (uiState is LoginUiState.Success) {
        LaunchedEffect(Unit) {
            onNavigateToDictionary()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = PaddingMedium)
            .noRippleClickable {
                focusManager.clearFocus()
            }
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.img_sign_up),
            contentDescription = null
        )

        Text(
            text = stringResource(id = R.string.signIn),
            style = HeadingH4
        )

        LoginForm(viewModel = viewModel)

        Spacer(modifier = Modifier.weight(1.5f))

        if (uiState is LoginUiState.Loading) {
            LoadingPrimaryButton()
        } else {
            PrimaryButton(
                onClick = viewModel::onLogin,
                enabled = loginState.isLoginEnabled,
                text = stringResource(id = R.string.signIn)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(PaddingTiny)
        ) {
            Text(
                modifier = Modifier.padding(bottom = PaddingRegular),
                text = stringResource(id = R.string.noAccount),
                style = ParagraphMedium
            )

            Text(
                modifier = Modifier
                    .padding(bottom = PaddingRegular)
                    .noRippleClickable {
                        onNavigateToRegistration()
                    },
                text = stringResource(id = R.string.signUp),
                style = ParagraphMedium,
                color = SecondaryColor
            )
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen({}, {})
}