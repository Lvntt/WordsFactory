package dev.lantt.wordsfactory.dictionary.data.repository

import dev.lantt.wordsfactory.dictionary.data.network.DictionaryApiService
import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository

class DictionaryRepositoryImpl(
    private val dictionaryApiService: DictionaryApiService
) : DictionaryRepository {

    override suspend fun getDictionaryWords(query: String): DictionaryWord {
        val apiResult = dictionaryApiService.getDictionaryWord(query)
        // return mapper.toDomain()
        TODO()
    }

    override suspend fun saveDictionaryWord(word: DictionaryWord) {
        TODO("Not yet implemented")
    }

}