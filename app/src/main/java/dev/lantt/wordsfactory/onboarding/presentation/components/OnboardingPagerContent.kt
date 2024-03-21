package dev.lantt.wordsfactory.onboarding.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.lantt.wordsfactory.core.presentation.ui.theme.HeadingH4
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkDark
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkDarkGray
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingSmall
import dev.lantt.wordsfactory.core.presentation.ui.theme.ParagraphMedium
import dev.lantt.wordsfactory.onboarding.presentation.data.OnboardingPageItem

@Composable
fun OnboardingPagerContent(
    onboardingPageItem: OnboardingPageItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(264.dp),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = onboardingPageItem.imageId),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(PaddingMedium))

        Column(
            modifier = Modifier.height(144.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = onboardingPageItem.titleId),
                style = HeadingH4,
                color = InkDark,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(PaddingSmall))

            Text(
                text = stringResource(id = onboardingPageItem.bodyId),
                style = ParagraphMedium,
                color = InkDarkGray,
                textAlign = TextAlign.Center
            )
        }
    }
}