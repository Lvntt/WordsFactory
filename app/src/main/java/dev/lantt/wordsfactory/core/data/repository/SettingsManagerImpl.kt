package dev.lantt.wordsfactory.core.data.repository

import dev.lantt.wordsfactory.core.data.datasource.SettingsDataSource
import dev.lantt.wordsfactory.core.domain.repository.SettingsManager

class SettingsManagerImpl(
    private val settingsDataSource: SettingsDataSource
) : SettingsManager {

    override suspend fun setOnboardingPassed(isOnboardingPassed: Boolean) {
        settingsDataSource.setIsOnboardingPassed(isOnboardingPassed)
    }

    override fun isOnboardingPassed(): Boolean {
        return settingsDataSource.isOnboardingPassed()
    }

    override suspend fun setLastTrainingTime(timeMillis: Long) {
        return settingsDataSource.setLastTrainingTime(timeMillis)
    }

    override fun getLastTrainingTimeMillis(): Long {
        return settingsDataSource.getLastTrainingTimeMillis()
    }

    override suspend fun setNotificationPermissionAsked() {
        return settingsDataSource.setNotificationPermissionAsked()
    }

    override fun wasNotificationPermissionAsked(): Boolean {
        return settingsDataSource.wasNotificationPermissionAsked()
    }

}