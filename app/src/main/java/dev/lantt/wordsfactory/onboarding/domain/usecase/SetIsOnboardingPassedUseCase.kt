package dev.lantt.wordsfactory.onboarding.domain.usecase

import dev.lantt.wordsfactory.core.domain.repository.SettingsRepository

class SetIsOnboardingPassedUseCase(
    private val settingsRepository: SettingsRepository
) {

    suspend operator fun invoke(isOnboardingPassed: Boolean) =
        settingsRepository.setOnboardingPassed(isOnboardingPassed)

}