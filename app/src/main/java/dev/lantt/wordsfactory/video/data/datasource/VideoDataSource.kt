package dev.lantt.wordsfactory.video.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking

const val DATA_STORE_NAME = "video_data_store"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

class VideoDataSource(private val context: Context) {

    fun fetchUrl(): String? {
        val url: MutableStateFlow<String?> = MutableStateFlow(null)

        runBlocking {
            url.update {
                context.dataStore.data.map { prefs ->
                    prefs[FIELD_VIDEO_URL]
                }.first()
            }
        }

        return url.value
    }

    suspend fun saveUrl(url: String) {
        context.dataStore.edit { prefs ->
            prefs[FIELD_VIDEO_URL] = url
        }
    }

    private companion object {
        const val FIELD_VIDEO_URL_KEY = "url"
        val FIELD_VIDEO_URL = stringPreferencesKey(FIELD_VIDEO_URL_KEY)
    }

}