package dev.lantt.wordsfactory.dictionary.data.mock

import dev.lantt.wordsfactory.dictionary.domain.entity.Definition
import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.dictionary.domain.entity.Meaning

object MockDictionaryWords {
    val words = listOf(
        DictionaryWord(
            word = "Сooking",
            phonetic = null,
            phoneticAudio = null,
            partsOfSpeech = emptyList(),
            meanings = listOf(
                Meaning(
                    partOfSpeech = "noun",
                    definition = Definition(
                        definition = "The practice or skill of preparing food by combining, mixing, and heating ingredients.",
                        example = null
                    )
                )
            ),
            isCached = true,
            learningCoefficient = 0
        ),
        DictionaryWord(
            word = "Smiling",
            phonetic = null,
            phoneticAudio = null,
            partsOfSpeech = emptyList(),
            meanings = listOf(
                Meaning(
                    partOfSpeech = "noun",
                    definition = Definition(
                        definition = "To look cheerful and joyous; to have an appearance suited to excite joy.",
                        example = null
                    )
                )
            ),
            isCached = true,
            learningCoefficient = 0
        ),
        DictionaryWord(
            word = "Freezing",
            phonetic = null,
            phoneticAudio = null,
            partsOfSpeech = emptyList(),
            meanings = listOf(
                Meaning(
                    partOfSpeech = "noun",
                    definition = Definition(
                        definition = "Especially of a liquid, to become solid due to low temperature.",
                        example = null
                    )
                )
            ),
            isCached = true,
            learningCoefficient = 0
        ),
    )
}