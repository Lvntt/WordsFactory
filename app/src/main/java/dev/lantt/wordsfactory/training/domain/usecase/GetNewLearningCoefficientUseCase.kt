package dev.lantt.wordsfactory.training.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord


class GetNewLearningCoefficientUseCase {

    operator fun invoke(
        correctWord: DictionaryWord,
        option: String
    ): Int {
        return if (correctWord.word == option) {
            correctWord.learningCoefficient + 1
        } else {
            correctWord.learningCoefficient - 1
        }
    }

}