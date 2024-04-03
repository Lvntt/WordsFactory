package dev.lantt.wordsfactory.dictionary.data.model.remote

data class DefinitionDto(
    val definition: String,
    val example: String?,
    val synonyms: List<String>,
    val antonyms: List<String>
)
