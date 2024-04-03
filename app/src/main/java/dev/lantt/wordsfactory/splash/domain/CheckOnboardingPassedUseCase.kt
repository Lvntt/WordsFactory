package dev.lantt.wordsfactory.splash.domain

import dev.lantt.wordsfactory.core.domain.repository.SettingsRepository

class CheckOnboardingPassedUseCase(
    private val settingsRepository: SettingsRepository
) {

    operator fun invoke(): Boolean = settingsRepository.isOnboardingPassed()

}