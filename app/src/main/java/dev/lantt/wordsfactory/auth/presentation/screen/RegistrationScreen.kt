package dev.lantt.wordsfactory.auth.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.auth.presentation.components.RegistrationForm
import dev.lantt.wordsfactory.core.presentation.ui.shared.PrimaryButton
import dev.lantt.wordsfactory.core.presentation.ui.theme.HeadingH4
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingRegular
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingSmall
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingTiny
import dev.lantt.wordsfactory.core.presentation.ui.theme.ParagraphMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.SecondaryColor
import dev.lantt.wordsfactory.core.presentation.util.noRippleClickable

@Composable
fun RegistrationScreen(
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = PaddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.img_sign_up),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(PaddingMedium))

        Text(
            text = stringResource(id = R.string.signUp),
            style = HeadingH4
        )

        Spacer(modifier = Modifier.height(PaddingSmall))

        Text(
            text = stringResource(id = R.string.createYourAccount),
            style = ParagraphMedium
        )

        Spacer(modifier = Modifier.height(PaddingMedium))

        RegistrationForm()

        Spacer(modifier = Modifier.weight(1.5f))

        PrimaryButton(
            modifier = Modifier.padding(vertical = PaddingMedium),
            onClick = { /*TODO*/ },
            text = stringResource(id = R.string.signUp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(PaddingTiny)
        ) {
            Text(
                modifier = Modifier.padding(bottom = PaddingRegular),
                text = stringResource(id = R.string.alreadyHaveAnAccount),
                style = ParagraphMedium
            )

            Text(
                modifier = Modifier
                    .padding(bottom = PaddingRegular)
                    .noRippleClickable {
                        onNavigateToLogin()
                    },
                text = stringResource(id = R.string.signIn),
                style = ParagraphMedium,
                color = SecondaryColor
            )
        }
    }
}

@Preview
@Composable
private fun RegistrationScreenPreview() {
    RegistrationScreen({})
}