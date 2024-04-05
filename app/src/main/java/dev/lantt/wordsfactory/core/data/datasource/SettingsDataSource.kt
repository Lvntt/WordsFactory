package dev.lantt.wordsfactory.core.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking

const val DATA_STORE_NAME = "settings_data_store"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

// TODO SettingsManager in domain?
class SettingsDataSource(private val context: Context) {

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

    suspend fun setIsOnboardingPassed(isOnboardingPassed: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[FIELD_IS_ONBOARDING_PASSED] = isOnboardingPassed
        }
    }

    private companion object {
        const val FIELD_IS_ONBOARDING_PASSED_KEY = "is_onboarding_passed"
        val FIELD_IS_ONBOARDING_PASSED = booleanPreferencesKey(FIELD_IS_ONBOARDING_PASSED_KEY)
    }

}