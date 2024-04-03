package dev.lantt.wordsfactory.onboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.onboarding.domain.usecase.SetIsOnboardingPassedUseCase
import dev.lantt.wordsfactory.onboarding.presentation.data.OnboardingPageItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val setIsOnboardingPassedUseCase: SetIsOnboardingPassedUseCase
) : ViewModel() {

    val onboardingItems = OnboardingPageItems.items

    fun onPassOnboarding() {
        viewModelScope.launch(Dispatchers.IO) {
            setIsOnboardingPassedUseCase(isOnboardingPassed = true)
        }
    }

}