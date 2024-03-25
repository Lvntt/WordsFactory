package dev.lantt.wordsfactory.dictionary.domain.repository

import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord

interface DictionaryRepository {

    suspend fun getDictionaryWords(query: String): DictionaryWord

    suspend fun saveDictionaryWord(word: DictionaryWord)

}