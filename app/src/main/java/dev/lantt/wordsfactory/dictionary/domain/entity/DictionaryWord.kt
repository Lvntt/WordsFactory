package dev.lantt.wordsfactory.dictionary.domain.entity

data class DictionaryWord(
    val word: String,
    val phonetic: String?,
    val phoneticAudio: String?,
    val partsOfSpeech: List<String>,
    val meanings: List<Meaning>,
    val isCached: Boolean,
    val learningCoefficient: Int
)
