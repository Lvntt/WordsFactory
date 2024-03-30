package dev.lantt.wordsfactory.video.domain.usecase

import dev.lantt.wordsfactory.video.domain.repository.VideoRepository

class FetchLastSavedUrlUseCase(
    private val videoRepository: VideoRepository
) {

    operator fun invoke() = videoRepository.fetchLastSavedUrl()

}