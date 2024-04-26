package dev.lantt.wordsfactory.dictionary.data.mapper

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import dev.lantt.wordsfactory.dictionary.data.model.local.DefinitionEntity
import dev.lantt.wordsfactory.dictionary.data.model.local.DictionaryWordEntity
import dev.lantt.wordsfactory.dictionary.data.model.local.DictionaryWordWithMeanings
import dev.lantt.wordsfactory.dictionary.data.model.local.MeaningEntity
import dev.lantt.wordsfactory.dictionary.data.model.remote.DictionaryWordDto
import dev.lantt.wordsfactory.dictionary.domain.entity.Definition
import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.dictionary.domain.entity.Meaning

class DictionaryWordMapper {

    fun mapDtoToDomain(data: DictionaryWordDto): DictionaryWord {
        with(data) {
            val word = word.capitalize(Locale.current)
            val phonetic = getPhonetic()
            val phoneticAudio = getPhoneticAudio()
            val partsOfSpeech = getPartsOfSpeech()
            val meanings = getMeanings()

            return DictionaryWord(
                word = word,
                phonetic = phonetic,
                phoneticAudio = phoneticAudio,
                partsOfSpeech = partsOfSpeech,
                meanings = meanings,
                isCached = false,
                learningCoefficient = 0
            )
        }
    }

    fun mapEntityToDomain(entity: DictionaryWordWithMeanings): DictionaryWord {
        val word = entity.dictionaryWord.word.capitalize(Locale.current)
        val partsOfSpeech = entity.getPartsOfSpeech()
        val meanings = entity.getMeanings()

        return DictionaryWord(
            word = word,
            phonetic = entity.dictionaryWord.phonetic,
            phoneticAudio = entity.dictionaryWord.phoneticAudio,
            partsOfSpeech = partsOfSpeech,
            meanings = meanings,
            isCached = true,
            learningCoefficient = entity.dictionaryWord.learningCoefficient
        )
    }

    fun mapDomainToDictionaryWordEntity(domain: DictionaryWord): DictionaryWordEntity {
        return DictionaryWordEntity(
            word = domain.word.toLowerCase(Locale.current),
            phonetic = domain.phonetic,
            phoneticAudio = domain.phoneticAudio,
            learningCoefficient = domain.learningCoefficient
        )
    }

    fun mapDomainMeaningToDefinitionEntity(domain: Meaning): DefinitionEntity {
        return DefinitionEntity(
            definition = domain.definition.definition,
            example = domain.definition.example
        )
    }

    fun mapDomainMeaningToMeaningEntity(
        domain: Meaning,
        parentWord: DictionaryWord,
        definition: DefinitionEntity
    ): MeaningEntity {
        return MeaningEntity(
            word = parentWord.word.toLowerCase(Locale.current),
            partOfSpeech = domain.partOfSpeech,
            definition = definition
        )
    }

    private fun DictionaryWordDto.getPhonetic(): String? {
        if (phonetic != null) {
            return phonetic
        }

        val phonetic = phonetics.firstOrNull {
            it.text != null
        }

        return phonetic?.text?.capitalize(Locale.current)
    }

    private fun DictionaryWordDto.getPhoneticAudio(): String? {
        val phoneticAudio = phonetics.firstOrNull {
            !it.audio.isNullOrBlank()
        }

        return phoneticAudio?.audio
    }

    private fun DictionaryWordDto.getPartsOfSpeech(): List<String> {
        return meanings.map { it.partOfSpeech.capitalize(Locale.current) }
    }

    private fun DictionaryWordWithMeanings.getPartsOfSpeech(): List<String> {
        return meanings.map { it.partOfSpeech.capitalize(Locale.current) }
    }

    private fun DictionaryWordDto.getMeanings(): List<Meaning> {
        return meanings.map { meaningDto ->
            Meaning(
                partOfSpeech = meaningDto.partOfSpeech.capitalize(Locale.current),
                definition = Definition(
                    definition = meaningDto.definitions[0].definition,
                    example = meaningDto.definitions[0].example
                )
            )
        }
    }

    private fun DictionaryWordWithMeanings.getMeanings(): List<Meaning> {
        return meanings.map { meaningDto ->
            Meaning(
                partOfSpeech = meaningDto.partOfSpeech.capitalize(Locale.current),
                definition = Definition(
                    definition = meaningDto.definition.definition,
                    example = meaningDto.definition.example
                )
            )
        }
    }

}