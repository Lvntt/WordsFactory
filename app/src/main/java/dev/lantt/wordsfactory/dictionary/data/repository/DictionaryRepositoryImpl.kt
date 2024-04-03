package dev.lantt.wordsfactory.dictionary.data.repository

import dev.lantt.wordsfactory.dictionary.data.mapper.DictionaryWordMapper
import dev.lantt.wordsfactory.dictionary.data.network.DictionaryApiService
import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository

class DictionaryRepositoryImpl(
    private val dictionaryApiService: DictionaryApiService,
    private val dictionaryWordMapper: DictionaryWordMapper
) : DictionaryRepository {

    override suspend fun getDictionaryWord(query: String): DictionaryWord {
        val apiResult = dictionaryApiService.getDictionaryWord(query)
        return dictionaryWordMapper.mapToDomain(apiResult[0])
    }

    override suspend fun saveDictionaryWord(word: DictionaryWord) {
        TODO("Not yet implemented")
    }

}