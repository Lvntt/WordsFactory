package dev.lantt.wordsfactory.training.domain.entity

import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord

data class Question(
    val correctWord: DictionaryWord,
    val correctWordDefinition: String,
    val options: List<String>
)