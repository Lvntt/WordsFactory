package dev.lantt.wordsfactory.dictionary.data.repository

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import dev.lantt.wordsfactory.dictionary.data.dao.DictionaryDao
import dev.lantt.wordsfactory.dictionary.data.mapper.DictionaryWordMapper
import dev.lantt.wordsfactory.dictionary.data.network.DictionaryApiService
import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DictionaryRepositoryImpl(
    private val dictionaryApiService: DictionaryApiService,
    private val dictionaryWordMapper: DictionaryWordMapper,
    private val dictionaryDao: DictionaryDao
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
    }

    override fun getAllSavedDictionaryWords(): Flow<List<DictionaryWord>> {
        return dictionaryDao.getAllDictionaryWordsWithMeanings().map { dbEntities ->
            dbEntities.map { dbEntity ->
                dictionaryWordMapper.mapEntityToDomain(dbEntity)
            }
        }
    }

}