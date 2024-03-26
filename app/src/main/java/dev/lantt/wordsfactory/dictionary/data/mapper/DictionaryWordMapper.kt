package dev.lantt.wordsfactory.dictionary.data.mapper

import android.content.Context
import dev.lantt.wordsfactory.dictionary.data.model.DictionaryWordDto
import dev.lantt.wordsfactory.dictionary.domain.entity.Definition
import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.dictionary.domain.entity.Meaning

class DictionaryWordMapper(
    context: Context
) {

    private val locale = context.resources.configuration.locales[0]

    fun mapToDomain(data: DictionaryWordDto): DictionaryWord {
        with(data) {
            val word = word.capitalize()
            val phonetic = getPhonetic()
            val phoneticAudio = getPhoneticAudio()
            val partsOfSpeech = getPartsOfSpeech()
            val meanings = getMeanings()

            return DictionaryWord(
                word = word,
                phonetic = phonetic,
                phoneticAudio = phoneticAudio,
                partsOfSpeech = partsOfSpeech,
                meanings = meanings
            )
        }
    }

    private fun String.capitalize(): String {
        return replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }
    }

    private fun DictionaryWordDto.getPhonetic(): String? {
        if (phonetic != null) {
            return phonetic
        }

        val phonetic = phonetics.firstOrNull {
            it.text != null
        }

        return phonetic?.text?.capitalize()
    }

    private fun DictionaryWordDto.getPhoneticAudio(): String? {
        val phoneticAudio = phonetics.firstOrNull {
            it.audio != null
        }

        return phoneticAudio?.audio
    }

    private fun DictionaryWordDto.getPartsOfSpeech(): List<String> {
        return meanings.map { it.partOfSpeech.capitalize() }
    }

    private fun DictionaryWordDto.getMeanings(): List<Meaning> {
        return meanings.map { meaningDto ->
            Meaning(
                partOfSpeech = meaningDto.partOfSpeech.capitalize(),
                definition = Definition(
                    definition = meaningDto.definitions[0].definition,
                    example = meaningDto.definitions[0].example
                )
            )
        }
    }

}