package dev.lantt.wordsfactory.onboarding.presentation.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.lantt.wordsfactory.core.presentation.ui.theme.PagerIndicatorDotActive
import dev.lantt.wordsfactory.core.presentation.ui.theme.PagerIndicatorDotInactive
import dev.lantt.wordsfactory.core.presentation.ui.theme.PagerIndicatorSize
import dev.lantt.wordsfactory.core.presentation.ui.theme.PagerLongIndicatorSize

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPagerIndicator(
    pageCount: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) {
            val width = animateDpAsState(
                targetValue = if (pagerState.currentPage == it) PagerLongIndicatorSize
                else PagerIndicatorSize,
                label = "width",
                animationSpec = tween(150, easing = LinearOutSlowInEasing)
            )

            val color = if (pagerState.currentPage == it) PagerIndicatorDotActive
            else PagerIndicatorDotInactive

            Box(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .clip(RoundedCornerShape(size = PagerIndicatorSize))
                    .background(color)
                    .width(width.value)
                    .height(PagerIndicatorSize)
            )
        }
    }
}