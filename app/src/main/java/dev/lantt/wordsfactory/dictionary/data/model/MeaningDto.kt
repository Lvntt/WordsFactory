package dev.lantt.wordsfactory.dictionary.data.model

data class MeaningDto(
    val partOfSpeech: String,
    val definitions: List<DefinitionDto>,
    val synonyms: List<String>,
    val antonyms: List<String>
)