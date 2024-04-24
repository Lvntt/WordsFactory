package dev.lantt.wordsfactory.training.domain.usecase

import dev.lantt.wordsfactory.dictionary.data.mock.MockDictionaryWords
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetTestQuestionsUseCaseTest {

    @Test
    fun `GIVEN different words WHEN generate test EXPECT different questions`() {
        val dictionaryWords = MockDictionaryWords.words
        val getTestQuestionsUseCase = GetTestQuestionsUseCase()

        val questions = getTestQuestionsUseCase(dictionaryWords)

        assertEquals(questions.size, questions.toSet().size)
    }

    @Test
    fun `GIVEN N words WHEN generate test EXPECT N questions`() {
        val dictionaryWords = MockDictionaryWords.words
        val getTestQuestionsUseCase = GetTestQuestionsUseCase()
        val expectedSize = dictionaryWords.size

        val questions = getTestQuestionsUseCase(dictionaryWords)

        assertTrue(questions.size == expectedSize)
    }

    @Test
    fun `GIVEN different words WHEN generate test EXPECT each question has only 1 correct answer`() {
        val dictionaryWords = MockDictionaryWords.words
        val getTestQuestionsUseCase = GetTestQuestionsUseCase()

        val questions = getTestQuestionsUseCase(dictionaryWords)

        assertTrue(
            questions.all { question ->
                question.options.count { it == question.correctWord.word } == 1
            }
        )
    }

    @Test
    fun `GIVEN different words WHEN generate test EXPECT each question has 2 incorrect answers`() {
        val dictionaryWords = MockDictionaryWords.words
        val getTestQuestionsUseCase = GetTestQuestionsUseCase()

        val questions = getTestQuestionsUseCase(dictionaryWords)

        assertTrue(
            questions.all { question ->
                question.options.count { it != question.correctWord.word } == 2
            }
        )
    }

    @Test
    fun `WHEN generate test EXPECT each question has non-blank definition`() {
        val dictionaryWords = MockDictionaryWords.words
        val getTestQuestionsUseCase = GetTestQuestionsUseCase()

        val questions = getTestQuestionsUseCase(dictionaryWords)

        assertTrue(
            questions.all { it.correctWordDefinition.isNotBlank() }
        )
    }

}