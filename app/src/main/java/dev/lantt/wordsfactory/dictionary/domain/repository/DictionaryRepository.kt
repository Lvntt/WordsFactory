package dev.lantt.wordsfactory.dictionary.domain.repository

import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord

interface DictionaryRepository {

    suspend fun getDictionaryWord(query: String): DictionaryWord

    suspend fun saveDictionaryWord(word: DictionaryWord)

}