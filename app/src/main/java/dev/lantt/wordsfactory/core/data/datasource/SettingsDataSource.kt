package dev.lantt.wordsfactory.core.data.datasource

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking

// TODO SettingsManager in domain?
class SettingsDataSource(context: Context) {

    private val dataStore = context.createDataStore(DATA_STORE_NAME)

    fun isOnboardingPassed(): Boolean {
        val isOnboardingPassed: MutableStateFlow<Boolean?> = MutableStateFlow(false)

        runBlocking {
            isOnboardingPassed.update {
                dataStore.data.map { prefs ->
                    prefs[FIELD_IS_ONBOARDING_PASSED]
                }.first()
            }
        }

        return isOnboardingPassed.value ?: false
    }

    suspend fun setIsOnboardingPassed(isOnboardingPassed: Boolean) {
        dataStore.edit { prefs ->
            prefs[FIELD_IS_ONBOARDING_PASSED] = isOnboardingPassed
        }
    }

    private companion object {
        const val DATA_STORE_NAME = "settings_data_store"

        const val FIELD_IS_ONBOARDING_PASSED_KEY = "is_onboarding_passed"
        val FIELD_IS_ONBOARDING_PASSED = preferencesKey<Boolean>(FIELD_IS_ONBOARDING_PASSED_KEY)
    }

}