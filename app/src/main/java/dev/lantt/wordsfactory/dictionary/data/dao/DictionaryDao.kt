package dev.lantt.wordsfactory.dictionary.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import dev.lantt.wordsfactory.dictionary.data.model.local.DefinitionEntity
import dev.lantt.wordsfactory.dictionary.data.model.local.DictionaryWordEntity
import dev.lantt.wordsfactory.dictionary.data.model.local.DictionaryWordWithMeanings
import dev.lantt.wordsfactory.dictionary.data.model.local.MeaningEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao {

    @Upsert
    suspend fun upsertDictionaryWord(dictionaryWord: DictionaryWordEntity)

    @Upsert
    suspend fun upsertMeaning(meaning: MeaningEntity)

    @Upsert
    suspend fun upsertDefinition(definition: DefinitionEntity)

    @Transaction
    @Query("SELECT * FROM dictionary_words")
    fun getAllDictionaryWordsWithMeanings(): Flow<List<DictionaryWordWithMeanings>>

}