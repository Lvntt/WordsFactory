package dev.lantt.wordsfactory.dictionary.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository

class GetAllSavedDictionaryWordsUseCase(
    private val dictionaryRepository: DictionaryRepository
) {

    operator fun invoke() = dictionaryRepository.getAllSavedDictionaryWords()

}