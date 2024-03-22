package dev.lantt.wordsfactory.onboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dev.lantt.wordsfactory.onboarding.presentation.data.OnboardingPageItems

class OnboardingViewModel : ViewModel() {

    val onboardingItems = OnboardingPageItems.items

}