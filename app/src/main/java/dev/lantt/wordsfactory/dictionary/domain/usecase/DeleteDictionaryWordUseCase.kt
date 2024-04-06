package dev.lantt.wordsfactory.dictionary.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository

class DeleteDictionaryWordUseCase(
    private val dictionaryRepository: DictionaryRepository
) {

    suspend operator fun invoke(word: String) =
        dictionaryRepository.deleteDictionaryWord(word)

}