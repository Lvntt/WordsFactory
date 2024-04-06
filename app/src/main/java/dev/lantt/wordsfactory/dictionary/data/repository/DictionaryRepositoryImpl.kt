package dev.lantt.wordsfactory.dictionary.data.repository

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
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

    // TODO to mapper
    override suspend fun getDictionaryWord(query: String): DictionaryWord {
        val cachedDictionaryWord = dictionaryDao.getDictionaryWordWithMeanings(query.toLowerCase(Locale.current))
        if (cachedDictionaryWord != null) {
            return DictionaryWord(
                word = cachedDictionaryWord.dictionaryWord.word.capitalize(Locale.current),
                phonetic = cachedDictionaryWord.dictionaryWord.phonetic,
                phoneticAudio = cachedDictionaryWord.dictionaryWord.phoneticAudio,
                partsOfSpeech = cachedDictionaryWord.meanings.map { it.partOfSpeech.capitalize(Locale.current) },
                meanings = cachedDictionaryWord.meanings.map { meaningDto ->
                    Meaning(
                        partOfSpeech = meaningDto.partOfSpeech.capitalize(Locale.current),
                        definition = Definition(
                            definition = meaningDto.definition.definition,
                            example = meaningDto.definition.example
                        )
                    )
                },
                isCached = true
            )
        }

        val apiResult = dictionaryApiService.getDictionaryWord(query)
        return dictionaryWordMapper.mapDtoToDomain(apiResult[0])
    }

    override suspend fun saveDictionaryWord(word: DictionaryWord) {
        val wordText = word.word.toLowerCase(Locale.current)

        val dictionaryWordEntity = DictionaryWordEntity(
            word = wordText,
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
                word = wordText,
                partOfSpeech = meaning.partOfSpeech,
                definition = definitionEntity
            )
            dictionaryDao.upsertMeaning(meaningEntity)
        }
    }

    override fun getAllSavedDictionaryWords(): Flow<List<DictionaryWord>> {
        return dictionaryDao.getAllDictionaryWordsWithMeanings().map { dbEntities ->
            dbEntities.map { dbEntity ->
                val wordText = dbEntity.dictionaryWord.word.toLowerCase(Locale.current)

                DictionaryWord(
                    word = wordText,
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
                    },
                    isCached = true
                )
            }
        }
    }

}