package dev.lantt.wordsfactory.dictionary.data.mapper

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
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
                isCached = false
            )
        }
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
            it.audio != null
        }

        return phoneticAudio?.audio
    }

    private fun DictionaryWordDto.getPartsOfSpeech(): List<String> {
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

}