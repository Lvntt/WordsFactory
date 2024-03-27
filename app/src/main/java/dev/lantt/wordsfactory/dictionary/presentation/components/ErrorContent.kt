package dev.lantt.wordsfactory.dictionary.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.theme.HeadingH5
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PrimaryColor

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_wifi_off),
            contentDescription = null,
            tint = PrimaryColor
        )

        Text(
            text = stringResource(id = R.string.connectionError),
            style = HeadingH5,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}