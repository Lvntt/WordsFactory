package dev.lantt.wordsfactory.dictionary.domain.entity

data class Definition(
    val definition: String,
    val example: String?,
    val synonyms: List<String>,
    val antonyms: List<String>
)
