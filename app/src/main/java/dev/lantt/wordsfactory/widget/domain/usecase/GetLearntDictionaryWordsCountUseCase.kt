package dev.lantt.wordsfactory.widget.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow

class GetLearntDictionaryWordsCountUseCase(
    private val dictionaryRepository: DictionaryRepository
) {

    operator fun invoke(): Flow<Int> = dictionaryRepository.getLearntDictionaryWordsCount()

}