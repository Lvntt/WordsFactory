package dev.lantt.wordsfactory.video.domain.usecase

import dev.lantt.wordsfactory.video.domain.repository.VideoRepository

class SaveUrlToLocalStorageUseCase(
    private val videoRepository: VideoRepository
) {

    suspend operator fun invoke(url: String) =
        videoRepository.saveUrlToLocalStorage(url)

}