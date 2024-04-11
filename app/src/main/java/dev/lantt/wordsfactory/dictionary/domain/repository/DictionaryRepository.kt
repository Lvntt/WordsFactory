package dev.lantt.wordsfactory.dictionary.domain.repository

import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {

    suspend fun getDictionaryWord(query: String): DictionaryWord

    suspend fun saveDictionaryWord(word: DictionaryWord)

    suspend fun deleteDictionaryWord(word: String)

    suspend fun updateDictionaryWord(word: DictionaryWord)

    fun getAllSavedDictionaryWords(): Flow<List<DictionaryWord>>

    fun getLeastLearntDictionaryWords(maxWords: Int): Flow<List<DictionaryWord>>

    fun getDictionaryWordsCount(): Flow<Int>

    fun getLearntDictionaryWordsCount(): Flow<Int>

}