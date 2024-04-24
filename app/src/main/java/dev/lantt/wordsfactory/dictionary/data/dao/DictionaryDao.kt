package dev.lantt.wordsfactory.dictionary.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
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

    @Update
    suspend fun updateDictionaryWord(dictionaryWord: DictionaryWordEntity)

    @Delete
    suspend fun deleteDictionaryWord(dictionaryWord: DictionaryWordEntity)

    @Upsert
    suspend fun upsertMeaning(meaning: MeaningEntity)

    @Delete
    suspend fun deleteMeaning(meaning: MeaningEntity)

    @Upsert
    suspend fun upsertDefinition(definition: DefinitionEntity)

    @Delete
    suspend fun deleteDefinition(definition: DefinitionEntity)

    @Transaction
    @Query("SELECT * FROM dictionary_words")
    fun getAllDictionaryWordsWithMeanings(): Flow<List<DictionaryWordWithMeanings>>

    @Transaction
    @Query("SELECT * FROM dictionary_words WHERE word = :word")
    fun getDictionaryWordWithMeanings(word: String): DictionaryWordWithMeanings?

    @Transaction
    @Query("SELECT * FROM dictionary_words ORDER BY learningCoefficient ASC LIMIT :maxWords")
    suspend fun getLeastLearntWordsWithMeanings(maxWords: Int): List<DictionaryWordWithMeanings>

    @Query("SELECT COUNT(*) FROM dictionary_words")
    fun getDictionaryWordsCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM dictionary_words WHERE learningCoefficient > 5")
    fun getLearntDictionaryWordsCount(): Flow<Int>

}