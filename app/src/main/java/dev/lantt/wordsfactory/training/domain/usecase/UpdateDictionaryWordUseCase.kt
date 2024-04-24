package dev.lantt.wordsfactory.training.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository

class UpdateDictionaryWordUseCase(
    private val dictionaryRepository: DictionaryRepository
) {

    suspend operator fun invoke(dictionaryWord: DictionaryWord) {
        dictionaryRepository.updateDictionaryWord(dictionaryWord)
    }

}