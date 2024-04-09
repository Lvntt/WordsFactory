package dev.lantt.wordsfactory.training.presentation.state

import dev.lantt.wordsfactory.training.domain.entity.Question

data class QuestionState(
    val currentQuestion: Question,
    val correctWordDefinition: String,
    val selectedWord: String?,
    val number: Int,
    val total: Int,
)