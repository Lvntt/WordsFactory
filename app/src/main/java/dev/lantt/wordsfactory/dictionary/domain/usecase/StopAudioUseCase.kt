package dev.lantt.wordsfactory.dictionary.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.repository.AudioRepository

class StopAudioUseCase(
    private val audioRepository: AudioRepository
) {

    operator fun invoke() = audioRepository.stop()

}