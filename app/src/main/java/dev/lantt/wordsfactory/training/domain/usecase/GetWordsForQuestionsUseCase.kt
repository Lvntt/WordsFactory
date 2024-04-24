package dev.lantt.wordsfactory.training.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository

class GetWordsForQuestionsUseCase(
    private val dictionaryRepository: DictionaryRepository
) {

    suspend operator fun invoke(): List<DictionaryWord> {
        val mockWords = dictionaryRepository.getMockDictionaryWords()
        val leastLearntDictionaryWords = dictionaryRepository.getLeastLearntDictionaryWords(MAX_QUESTIONS_COUNT)
        return if (leastLearntDictionaryWords.size < MIN_WORDS_FOR_TEST) {
            mockWords
        } else {
            leastLearntDictionaryWords
        }
    }

    private companion object {
        const val MIN_WORDS_FOR_TEST = 3
        const val MAX_QUESTIONS_COUNT = 10
    }

}