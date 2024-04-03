package dev.lantt.wordsfactory.video.data.repository

import dev.lantt.wordsfactory.video.data.datasource.VideoDataSource
import dev.lantt.wordsfactory.video.domain.repository.VideoRepository

class VideoRepositoryImpl(
    private val videoDataSource: VideoDataSource
) : VideoRepository {

    override fun fetchLastSavedUrl(): String? =
        videoDataSource.fetchUrl()

    override suspend fun saveUrlToLocalStorage(url: String) {
        videoDataSource.saveUrl(url)
    }

}