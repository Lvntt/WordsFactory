package dev.lantt.wordsfactory.onboarding.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.shared.PrimaryButton
import dev.lantt.wordsfactory.core.presentation.ui.theme.ButtonSmall
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkDarkGray
import dev.lantt.wordsfactory.core.presentation.ui.theme.Padding5
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingRegular
import dev.lantt.wordsfactory.onboarding.presentation.viewmodel.OnboardingViewModel
import dev.lantt.wordsfactory.onboarding.presentation.components.OnboardingPagerContent
import dev.lantt.wordsfactory.onboarding.presentation.components.OnboardingPagerIndicator
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onNavigateToAuth: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = koinViewModel(),
) {
    val pageItems = viewModel.onboardingItems
    val pageCount = pageItems.size
    val pagerState = rememberPagerState(pageCount = { pageCount })
    val coroutineScope = rememberCoroutineScope()

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
                .clickable {
                    onNavigateToAuth()
                },
            text = stringResource(id = R.string.skip),
            style = ButtonSmall,
            color = InkDarkGray
        )

        Spacer(modifier = Modifier.weight(1f))

        HorizontalPager(state = pagerState) { page ->
            val currentPageItem = pageItems[page]

            OnboardingPagerContent(
                onboardingPageItem = currentPageItem
            )
        }

        Spacer(modifier = Modifier.height(PaddingMedium))

        OnboardingPagerIndicator(
            pageCount = pageCount,
            pagerState = pagerState
        )

        Spacer(modifier = Modifier.weight(1.5f))

        PrimaryButton(
            modifier = Modifier.padding(PaddingRegular),
            onClick = {
                if (pagerState.currentPage == pageCount - 1) {
                    onNavigateToAuth()
                } else {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            text = if (pagerState.currentPage == pageCount - 1) stringResource(id = R.string.letsStart)
                else stringResource(id = R.string.next)
        )
    }
}