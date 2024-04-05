package dev.lantt.wordsfactory.dictionary.domain.repository

import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {

    suspend fun getDictionaryWord(query: String): DictionaryWord

    suspend fun saveDictionaryWord(word: DictionaryWord)

    fun getAllSavedDictionaryWords(): Flow<List<DictionaryWord>>

}