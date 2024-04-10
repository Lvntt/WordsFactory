package dev.lantt.wordsfactory.core.presentation.ui.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.theme.CornerRadiusMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkDark
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkDarkGray
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkGray
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkWhite
import dev.lantt.wordsfactory.core.presentation.ui.theme.ParagraphMedium

@Composable
fun InputTextFieldWithAction(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    trailingIcon: ImageVector,
    trailingIconDescription: String,
    onTrailingIconClick: () -> Unit,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(size = CornerRadiusMedium),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = InkGray,
            focusedIndicatorColor = InkGray,
            unfocusedTextColor = InkDarkGray,
            focusedTextColor = InkDarkGray,
            unfocusedPlaceholderColor = InkGray,
            focusedPlaceholderColor = InkGray,
            unfocusedContainerColor = InkWhite,
            focusedContainerColor = InkWhite,
        ),
        placeholder = {
            Text(
                text = placeholder,
                style = ParagraphMedium
            )
        },
        trailingIcon = {
            IconButton(
                onClick = onTrailingIconClick
            ) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = trailingIconDescription,
                    tint = InkDark
                )
            }
        },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        singleLine = true
    )
}

@Preview
@Composable
fun InputTextFieldWithActionPreview() {
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    InputTextFieldWithAction(
        value = password,
        onValueChange = { password = it },
        placeholder = stringResource(id = R.string.password),
        trailingIcon = ImageVector.vectorResource(
            id = if (isPasswordVisible) R.drawable.ic_password_hide
            else R.drawable.ic_password_show
        ),
        trailingIconDescription = stringResource(id = R.string.changePasswordVisibility),
        onTrailingIconClick = { isPasswordVisible = !isPasswordVisible },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}