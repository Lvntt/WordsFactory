package dev.lantt.wordsfactory.dictionary.domain.entity

data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition>
)