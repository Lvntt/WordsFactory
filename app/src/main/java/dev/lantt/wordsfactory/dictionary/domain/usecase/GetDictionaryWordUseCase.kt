package dev.lantt.wordsfactory.dictionary.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository

class GetDictionaryWordUseCase(
    private val dictionaryRepository: DictionaryRepository
) {

    suspend operator fun invoke(query: String): DictionaryWord
        = dictionaryRepository.getDictionaryWord(query)

}