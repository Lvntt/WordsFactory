package dev.lantt.wordsfactory.dictionary.data.repository

import dev.lantt.wordsfactory.dictionary.data.dao.DictionaryDao
import dev.lantt.wordsfactory.dictionary.data.mapper.DictionaryWordMapper
import dev.lantt.wordsfactory.dictionary.data.model.local.DefinitionEntity
import dev.lantt.wordsfactory.dictionary.data.model.local.DictionaryWordEntity
import dev.lantt.wordsfactory.dictionary.data.model.local.MeaningEntity
import dev.lantt.wordsfactory.dictionary.data.network.DictionaryApiService
import dev.lantt.wordsfactory.dictionary.domain.entity.Definition
import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.dictionary.domain.entity.Meaning
import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DictionaryRepositoryImpl(
    private val dictionaryApiService: DictionaryApiService,
    private val dictionaryWordMapper: DictionaryWordMapper,
    private val dictionaryDao: DictionaryDao
) : DictionaryRepository {

    override suspend fun getDictionaryWord(query: String): DictionaryWord {
        val apiResult = dictionaryApiService.getDictionaryWord(query)
        return dictionaryWordMapper.mapDtoToDomain(apiResult[0])
    }

    override suspend fun saveDictionaryWord(word: DictionaryWord) {
        val dictionaryWordEntity = DictionaryWordEntity(
            word = word.word,
            phonetic = word.phonetic,
            phoneticAudio = word.phoneticAudio
        )
        dictionaryDao.upsertDictionaryWord(dictionaryWordEntity)

        word.meanings.forEach { meaning ->
            val definitionEntity = DefinitionEntity(
                definition = meaning.definition.definition,
                example = meaning.definition.example
            )
            dictionaryDao.upsertDefinition(definitionEntity)

            val meaningEntity = MeaningEntity(
                word = word.word,
                partOfSpeech = meaning.partOfSpeech,
                definition = definitionEntity
            )
            dictionaryDao.upsertMeaning(meaningEntity)
        }
    }

    override fun getAllSavedDictionaryWords(): Flow<List<DictionaryWord>> {
        return dictionaryDao.getAllDictionaryWordsWithMeanings().map { dbEntities ->
            dbEntities.map { dbEntity ->
                DictionaryWord(
                    word = dbEntity.dictionaryWord.word,
                    phonetic = dbEntity.dictionaryWord.phonetic,
                    phoneticAudio = dbEntity.dictionaryWord.phoneticAudio,
                    partsOfSpeech = dbEntity.meanings.map { it.partOfSpeech }.distinct(),
                    meanings = dbEntity.meanings.map { dbMeaningEntity ->
                        Meaning(
                            partOfSpeech = dbMeaningEntity.partOfSpeech,
                            definition = Definition(
                                definition = dbMeaningEntity.definition.definition,
                                example = dbMeaningEntity.definition.example
                            )
                        )
                    }
                )
            }
        }
    }

}