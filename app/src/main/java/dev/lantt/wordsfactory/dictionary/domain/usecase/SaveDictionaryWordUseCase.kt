package dev.lantt.wordsfactory.dictionary.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository

class SaveDictionaryWordUseCase(
    private val dictionaryRepository: DictionaryRepository
) {

    suspend operator fun invoke(word: DictionaryWord) =
        dictionaryRepository.saveDictionaryWord(word)

}