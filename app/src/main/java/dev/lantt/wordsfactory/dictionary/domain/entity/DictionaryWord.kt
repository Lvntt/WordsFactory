package dev.lantt.wordsfactory.dictionary.domain.entity

data class DictionaryWord(
    val word: String,
    val phonetic: String,
    val phonetics: List<Phonetic>,
    val origin: String,
    val meanings: List<Meaning>
)
