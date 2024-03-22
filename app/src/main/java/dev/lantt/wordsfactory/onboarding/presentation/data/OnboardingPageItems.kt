package dev.lantt.wordsfactory.onboarding.presentation.data

import dev.lantt.wordsfactory.R

object OnboardingPageItems {
    val items = listOf(
        OnboardingPageItem(
            imageId = R.drawable.img_onboarding_1,
            titleId = R.string.onboardingFirstTitle,
            bodyId = R.string.onboardingBody
        ),
        OnboardingPageItem(
            imageId = R.drawable.img_onboarding_2,
            titleId = R.string.onboardingSecondTitle,
            bodyId = R.string.onboardingBody
        ),
        OnboardingPageItem(
            imageId = R.drawable.img_onboarding_3,
            titleId = R.string.onboardingThirdTitle,
            bodyId = R.string.onboardingBody
        ),
    )
}