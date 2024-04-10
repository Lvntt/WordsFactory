package dev.lantt.wordsfactory.dictionary.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository

class GetLeastLearntWordsUseCase(
    private val dictionaryRepository: DictionaryRepository
) {

    operator fun invoke(maxWords: Int) =
        dictionaryRepository.getLeastLearntDictionaryWords(maxWords)

}