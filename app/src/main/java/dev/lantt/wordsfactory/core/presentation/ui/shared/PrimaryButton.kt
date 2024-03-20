package dev.lantt.wordsfactory.core.presentation.ui.shared

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.lantt.wordsfactory.core.presentation.ui.theme.ButtonMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.CornerRadiusMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkWhite
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingLarge
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PrimaryColor

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(size = CornerRadiusMedium),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor,
            contentColor = InkWhite
        ),
        contentPadding = PaddingValues(
            horizontal = PaddingLarge,
            vertical = PaddingMedium
        )
    ) {
        Text(
            text = text,
            style = ButtonMedium
        )
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(
        onClick = {},
        text = "Next"
    )
}