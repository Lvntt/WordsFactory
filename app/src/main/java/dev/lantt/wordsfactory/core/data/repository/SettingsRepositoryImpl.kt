package dev.lantt.wordsfactory.core.data.repository

import dev.lantt.wordsfactory.core.data.datasource.SettingsDataSource
import dev.lantt.wordsfactory.core.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val settingsDataSource: SettingsDataSource
) : SettingsRepository {

    override suspend fun setOnboardingPassed(isOnboardingPassed: Boolean) {
        settingsDataSource.setIsOnboardingPassed(isOnboardingPassed)
    }

    override fun isOnboardingPassed(): Boolean {
        return settingsDataSource.isOnboardingPassed()
    }

}