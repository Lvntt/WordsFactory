package dev.lantt.wordsfactory.core.domain.repository

interface SettingsRepository {

    suspend fun setOnboardingPassed(isOnboardingPassed: Boolean)

    fun isOnboardingPassed(): Boolean

}