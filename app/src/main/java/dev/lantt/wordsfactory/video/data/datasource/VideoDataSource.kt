package dev.lantt.wordsfactory.video.data.datasource

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking

class VideoDataSource(context: Context) {

    private val dataStore = context.createDataStore(DATA_STORE_NAME)

    fun fetchUrl(): String? {
        val url: MutableStateFlow<String?> = MutableStateFlow(null)

        runBlocking {
            url.update {
                dataStore.data.map { prefs ->
                    prefs[FIELD_VIDEO_URL]
                }.first()
            }
        }

        return url.value
    }

    suspend fun saveUrl(url: String) {
        dataStore.edit { prefs ->
            prefs[FIELD_VIDEO_URL] = url
        }
    }

    private companion object {
        const val DATA_STORE_NAME = "video_data_store"

        const val FIELD_VIDEO_URL_KEY = "url"
        val FIELD_VIDEO_URL = preferencesKey<String>(FIELD_VIDEO_URL_KEY)
    }

}