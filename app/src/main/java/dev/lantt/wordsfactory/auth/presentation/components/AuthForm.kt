package dev.lantt.wordsfactory.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.shared.InputTextField
import dev.lantt.wordsfactory.core.presentation.ui.shared.InputTextFieldWithAction
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium

@Composable
fun AuthForm(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        InputTextField(
            value = "",
            onValueChange = {},
            placeholder = stringResource(id = R.string.name)
        )

        InputTextField(
            value = "",
            onValueChange = {},
            placeholder = stringResource(id = R.string.email)
        )

        InputTextFieldWithAction(
            value = "",
            onValueChange = {},
            placeholder = stringResource(id = R.string.password),
            trailingIcon = ImageVector.vectorResource(id = R.drawable.ic_password_show),
            trailingIconDescription = stringResource(id = R.string.changePasswordVisibility),
            onTrailingIconClick = { /*TODO*/ })
    }
}