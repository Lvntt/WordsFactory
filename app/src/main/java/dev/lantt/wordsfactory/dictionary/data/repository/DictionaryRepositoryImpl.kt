package dev.lantt.wordsfactory.dictionary.data.repository

import android.content.Context
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.glance.appwidget.updateAll
import dev.lantt.wordsfactory.dictionary.data.dao.DictionaryDao
import dev.lantt.wordsfactory.dictionary.data.mapper.DictionaryWordMapper
import dev.lantt.wordsfactory.dictionary.data.mock.MockDictionaryWords
import dev.lantt.wordsfactory.dictionary.data.network.DictionaryApiService
import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository
import dev.lantt.wordsfactory.widget.WordsFactoryWidget
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DictionaryRepositoryImpl(
    private val dictionaryApiService: DictionaryApiService,
    private val dictionaryWordMapper: DictionaryWordMapper,
    private val dictionaryDao: DictionaryDao,
    private val context: Context
) : DictionaryRepository {

    override suspend fun getDictionaryWord(query: String): DictionaryWord {
        val cachedDictionaryWord = dictionaryDao.getDictionaryWordWithMeanings(query.toLowerCase(Locale.current))
        if (cachedDictionaryWord != null) {
            return dictionaryWordMapper.mapEntityToDomain(cachedDictionaryWord)
        }

        val apiResult = dictionaryApiService.getDictionaryWord(query)
        return dictionaryWordMapper.mapDtoToDomain(apiResult[0])
    }

    override suspend fun saveDictionaryWord(word: DictionaryWord) {
        val dictionaryWordEntity = dictionaryWordMapper.mapDomainToDictionaryWordEntity(word)
        dictionaryDao.upsertDictionaryWord(dictionaryWordEntity)

        word.meanings.forEach { meaning ->
            val definitionEntity = dictionaryWordMapper.mapDomainMeaningToDefinitionEntity(meaning)
            dictionaryDao.upsertDefinition(definitionEntity)

            val meaningEntity = dictionaryWordMapper.mapDomainMeaningToMeaningEntity(meaning, word, definitionEntity)
            dictionaryDao.upsertMeaning(meaningEntity)
        }

        updateWidget(context)
    }

    override suspend fun deleteDictionaryWord(word: String) {
        val cachedDictionaryWord = dictionaryDao.getDictionaryWordWithMeanings(word.toLowerCase(Locale.current))
        if (cachedDictionaryWord != null) {
            dictionaryDao.deleteDictionaryWord(cachedDictionaryWord.dictionaryWord)

            cachedDictionaryWord.meanings.forEach { meaning ->
                dictionaryDao.deleteDefinition(meaning.definition)
                dictionaryDao.deleteMeaning(meaning)
            }
        }
    }

    override suspend fun updateDictionaryWord(word: DictionaryWord) {
        val dictionaryWordEntity = dictionaryWordMapper.mapDomainToDictionaryWordEntity(word)
        dictionaryDao.updateDictionaryWord(dictionaryWordEntity)

        updateWidget(context)
    }

    override fun getAllSavedDictionaryWords(): Flow<List<DictionaryWord>> {
        return dictionaryDao.getAllDictionaryWordsWithMeanings().map { dbEntities ->
            dbEntities.map { dbEntity ->
                dictionaryWordMapper.mapEntityToDomain(dbEntity)
            }
        }
    }

    override suspend fun getLeastLearntDictionaryWords(maxWords: Int): List<DictionaryWord> {
        return dictionaryDao.getLeastLearntWordsWithMeanings(maxWords).map { dbEntity ->
            dictionaryWordMapper.mapEntityToDomain(dbEntity)
        }
    }

    override fun getDictionaryWordsCount(): Flow<Int> {
        return dictionaryDao.getDictionaryWordsCount()
    }

    override fun getLearntDictionaryWordsCount(): Flow<Int> {
        return dictionaryDao.getLearntDictionaryWordsCount()
    }

    override fun getMockDictionaryWords(): List<DictionaryWord> {
        return MockDictionaryWords.words
    }

    private suspend fun updateWidget(context: Context) {
         WordsFactoryWidget().updateAll(context)
    }

}