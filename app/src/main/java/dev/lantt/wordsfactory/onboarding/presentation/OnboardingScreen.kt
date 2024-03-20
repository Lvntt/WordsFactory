package dev.lantt.wordsfactory.onboarding.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.theme.ButtonSmall
import dev.lantt.wordsfactory.core.presentation.ui.theme.HeadingH4
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkDark
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkDarkGray
import dev.lantt.wordsfactory.core.presentation.ui.theme.Padding5
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingRegular
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingSmall
import dev.lantt.wordsfactory.core.presentation.ui.theme.ParagraphMedium
import dev.lantt.wordsfactory.core.presentation.util.noRippleClickable

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel,
) {
    val pageItems = viewModel.onboardingItems
    val pagerState = rememberPagerState(pageCount = { pageItems.size })

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.End)
                .padding(
                    top = PaddingRegular,
                    bottom = Padding5,
                    end = PaddingMedium
                )
                .noRippleClickable {
                    // TODO
                },
            text = stringResource(id = R.string.skip),
            style = ButtonSmall,
            color = InkDarkGray
        )

        HorizontalPager(state = pagerState) { page ->
            val currentPageItem = pageItems[page]

            OnboardingContent(onboardingPageItem = currentPageItem)
        }
    }
}

@Composable
private fun OnboardingContent(
    onboardingPageItem: OnboardingPageItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.height(264.dp),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = onboardingPageItem.imageId),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(PaddingMedium))

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