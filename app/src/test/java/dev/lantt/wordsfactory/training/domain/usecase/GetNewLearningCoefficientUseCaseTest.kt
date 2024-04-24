package dev.lantt.wordsfactory.training.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetNewLearningCoefficientUseCaseTest {

    @Test
    fun `WHEN correct choice EXPECT learning coefficient increases by 1`() {
        val initialLearningCoefficient = 0
        val expectedLearningCoefficient = 1
        val correctOption = "correct"

        val correctWord = DictionaryWord(
            word = correctOption,
            phonetic = null,
            phoneticAudio = null,
            partsOfSpeech = emptyList(),
            meanings = emptyList(),
            isCached = false,
            learningCoefficient = initialLearningCoefficient
        )

        val getNewLearningCoefficientUseCase = GetNewLearningCoefficientUseCase()

        val newCoefficient = getNewLearningCoefficientUseCase(correctWord, correctOption)

        assertEquals(expectedLearningCoefficient, newCoefficient)
    }

    @Test
    fun `WHEN incorrect choice EXPECT learning coefficient decreases by 1`() {
        val initialLearningCoefficient = 0
        val expectedLearningCoefficient = -1
        val correctOption = "correct"
        val incorrectOption = "incorrect"

        val correctWord = DictionaryWord(
            word = correctOption,
            phonetic = null,
            phoneticAudio = null,
            partsOfSpeech = emptyList(),
            meanings = emptyList(),
            isCached = false,
            learningCoefficient = initialLearningCoefficient
        )

        val getNewLearningCoefficientUseCase = GetNewLearningCoefficientUseCase()

        val newCoefficient = getNewLearningCoefficientUseCase(correctWord, incorrectOption)

        assertEquals(expectedLearningCoefficient, newCoefficient)
    }

}