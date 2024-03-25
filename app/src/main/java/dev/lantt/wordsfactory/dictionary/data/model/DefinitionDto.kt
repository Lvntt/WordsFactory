package dev.lantt.wordsfactory.dictionary.data.model

data class DefinitionDto(
    val definition: String,
    val example: String?,
    val synonyms: List<String>,
    val antonyms: List<String>
)
