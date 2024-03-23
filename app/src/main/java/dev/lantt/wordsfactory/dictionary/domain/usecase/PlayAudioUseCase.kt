package dev.lantt.wordsfactory.dictionary.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.repository.AudioRepository

class PlayAudioUseCase(
    private val audioRepository: AudioRepository
) {

    suspend operator fun invoke(audioUrl: String) =
        audioRepository.play(audioUrl)

}