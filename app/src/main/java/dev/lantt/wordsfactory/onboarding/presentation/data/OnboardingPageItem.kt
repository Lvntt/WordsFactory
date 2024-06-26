package dev.lantt.wordsfactory.onboarding.presentation.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OnboardingPageItem(
    @DrawableRes val imageId: Int,
    @StringRes val titleId: Int,
    @StringRes val bodyId: Int,
)
