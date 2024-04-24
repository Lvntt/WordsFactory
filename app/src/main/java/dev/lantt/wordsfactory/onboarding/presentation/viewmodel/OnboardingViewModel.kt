package dev.lantt.wordsfactory.onboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.core.domain.repository.SettingsManager
import dev.lantt.wordsfactory.onboarding.presentation.data.OnboardingPageItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val settingsManager: SettingsManager
) : ViewModel() {

    val onboardingItems = OnboardingPageItems.items

    fun onPassOnboarding() {
        viewModelScope.launch(Dispatchers.IO) {
            settingsManager.setOnboardingPassed(isOnboardingPassed = true)
        }
    }

}