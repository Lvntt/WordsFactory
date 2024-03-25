package dev.lantt.wordsfactory.dictionary.data.model

data class DictionaryWordDto(
    val word: String,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val origin: String,
    val meanings: List<MeaningDto>
)
