package dev.lantt.wordsfactory.core.presentation.ui.shared

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.lantt.wordsfactory.core.presentation.ui.theme.ButtonHeightRegular
import dev.lantt.wordsfactory.core.presentation.ui.theme.CornerRadiusMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkWhite
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingLarge
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PrimaryColor

@Composable
fun LoadingPrimaryButton(
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(ButtonHeightRegular),
        onClick = {},
        enabled = false,
        shape = RoundedCornerShape(size = CornerRadiusMedium),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor,
            contentColor = InkWhite,
            disabledContainerColor = PrimaryColor,
            disabledContentColor = InkWhite
        ),
        contentPadding = PaddingValues(
            horizontal = PaddingLarge,
            vertical = PaddingMedium
        )
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            color = InkWhite
        )
    }
}

@Preview
@Composable
fun LoadingPrimaryButtonPreview() {
    LoadingPrimaryButton()
}