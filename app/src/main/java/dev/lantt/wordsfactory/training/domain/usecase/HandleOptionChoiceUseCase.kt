package dev.lantt.wordsfactory.training.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository
import dev.lantt.wordsfactory.training.domain.entity.Question

// TODO depends on dictionary feature. should part of dictionary be in core?

class HandleOptionChoiceUseCase(
    private val dictionaryRepository: DictionaryRepository
) {

    suspend operator fun invoke(
        question: Question,
        option: String
    ) {
        val correctWord = question.correctWord

        val updatedLearningCoefficient = if (correctWord.word == option) {
            correctWord.learningCoefficient + 1
        } else {
            // TODO max(0, dictionaryWord.learningCoefficient - 1)?
            correctWord.learningCoefficient - 1
        }

        dictionaryRepository.updateDictionaryWord(
            correctWord.copy(learningCoefficient = updatedLearningCoefficient)
        )
    }

}