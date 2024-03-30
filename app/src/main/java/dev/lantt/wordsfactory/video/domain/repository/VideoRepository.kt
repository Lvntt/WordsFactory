package dev.lantt.wordsfactory.video.domain.repository

interface VideoRepository {

    fun fetchLastSavedUrl(): String?

    suspend fun saveUrlToLocalStorage(url: String)

}