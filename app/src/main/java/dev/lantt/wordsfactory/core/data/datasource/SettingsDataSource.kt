package dev.lantt.wordsfactory.core.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking

const val DATA_STORE_NAME = "settings_data_store"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

class SettingsDataSource(private val context: Context) {

    suspend fun setIsOnboardingPassed(isOnboardingPassed: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[FIELD_IS_ONBOARDING_PASSED] = isOnboardingPassed
        }
    }

    fun isOnboardingPassed(): Boolean {
        val isOnboardingPassed: MutableStateFlow<Boolean?> = MutableStateFlow(false)

        runBlocking {
            isOnboardingPassed.update {
                context.dataStore.data.map { prefs ->
                    prefs[FIELD_IS_ONBOARDING_PASSED]
                }.first()
            }
        }

        return isOnboardingPassed.value ?: false
    }

    suspend fun setLastTrainingTime(timeMillis: Long) {
        context.dataStore.edit { prefs ->
            prefs[FIELD_LAST_TRAINING_TIME] = timeMillis
        }
    }

    fun getLastTrainingTimeMillis(): Long {
        val lastTrainingTime: MutableStateFlow<Long?> = MutableStateFlow(ERROR_TIME_MILLIS)

        runBlocking {
            lastTrainingTime.update {
                context.dataStore.data.map { prefs ->
                    prefs[FIELD_LAST_TRAINING_TIME]
                }.first()
            }
        }

        return lastTrainingTime.value ?: ERROR_TIME_MILLIS
    }

    suspend fun setNotificationPermissionAsked() {
        context.dataStore.edit { prefs ->
            prefs[FIELD_NOTIFICATION_PERMISSION_ASKED] = true
        }
    }

    fun wasNotificationPermissionAsked(): Boolean {
        val wasNotificationPermissionAsked: MutableStateFlow<Boolean?> = MutableStateFlow(false)

        runBlocking {
            wasNotificationPermissionAsked.update {
                context.dataStore.data.map { prefs ->
                    prefs[FIELD_NOTIFICATION_PERMISSION_ASKED]
                }.first()
            }
        }

        return wasNotificationPermissionAsked.value ?: false
    }

    private companion object {
        const val FIELD_IS_ONBOARDING_PASSED_KEY = "is_onboarding_passed"
        const val FIELD_LAST_TRAINING_TIME_KEY = "last_training_time"
        const val FIELD_NOTIFICATION_PERMISSION_ASKED_KEY = "notification_permission_asked"
        val FIELD_IS_ONBOARDING_PASSED = booleanPreferencesKey(FIELD_IS_ONBOARDING_PASSED_KEY)
        val FIELD_LAST_TRAINING_TIME = longPreferencesKey(FIELD_LAST_TRAINING_TIME_KEY)
        val FIELD_NOTIFICATION_PERMISSION_ASKED = booleanPreferencesKey(FIELD_NOTIFICATION_PERMISSION_ASKED_KEY)

        const val ERROR_TIME_MILLIS = -1L
    }

}