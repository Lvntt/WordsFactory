package dev.lantt.wordsfactory.auth.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.theme.ButtonMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.CornerRadiusSmall
import dev.lantt.wordsfactory.core.presentation.ui.theme.HeadingH5
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkWhite
import dev.lantt.wordsfactory.core.presentation.ui.theme.ParagraphMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PrimaryColor

@Composable
fun AuthErrorDialog(
    onDismiss: () -> Unit,
    errorTitle: String,
    errorText: String
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_error),
                tint = PrimaryColor,
                contentDescription = null
            )
        },
        title = {
            Text(
                text = errorTitle,
                style = HeadingH5,
                color = Color.Black
            )
        },
        text = {
            Text(
                text = errorText,
                style = ParagraphMedium,
                color = Color.Black
            )
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                shape = RoundedCornerShape(CornerRadiusSmall),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor,
                    contentColor = InkWhite
                )
            ) {
                Text(
                    text = stringResource(id = R.string.ok),
                    style = ButtonMedium,
                    color = InkWhite
                )
            }
        },
        containerColor = InkWhite
    )
}