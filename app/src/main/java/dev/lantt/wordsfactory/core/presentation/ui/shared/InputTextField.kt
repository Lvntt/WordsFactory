package dev.lantt.wordsfactory.core.presentation.ui.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.theme.CornerRadiusMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkDarkGray
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkGray
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkWhite
import dev.lantt.wordsfactory.core.presentation.ui.theme.ParagraphMedium

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
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
        keyboardOptions = keyboardOptions
    )
}

@Preview
@Composable
fun InputTextFieldPreview() {
    var text by remember { mutableStateOf("") }

    InputTextField(
        value = text,
        onValueChange = { text = it },
        placeholder = stringResource(id = R.string.name),
    )
}