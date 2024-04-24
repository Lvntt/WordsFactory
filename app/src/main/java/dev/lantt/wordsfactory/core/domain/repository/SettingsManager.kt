package dev.lantt.wordsfactory.core.domain.repository

interface SettingsManager {

    suspend fun setOnboardingPassed(isOnboardingPassed: Boolean)

    fun isOnboardingPassed(): Boolean

    suspend fun setLastTrainingTime(timeMillis: Long)

    fun getLastTrainingTimeMillis(): Long

    suspend fun setNotificationPermissionAsked()

    fun wasNotificationPermissionAsked(): Boolean

}