package dev.lantt.wordsfactory.training.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.entity.Definition
import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.dictionary.domain.entity.Meaning
import dev.lantt.wordsfactory.training.domain.entity.Question

class GetTestQuestionsUseCase {

    // TODO depends on dictionary feature. should part of dictionary be in core?

    private val mockQuestions = listOf(
        Question(
            correctWord = DictionaryWord(
                word = "cooking",
                phonetic = null,
                phoneticAudio = null,
                partsOfSpeech = emptyList(),
                meanings = listOf(
                    Meaning(
                        partOfSpeech = "noun",
                        definition = Definition(
                            definition = "The practice or skill of preparing food by combining, mixing, and heating ingredients.",
                            example = null
                        )
                    )
                ),
                isCached = true,
                learningCoefficient = 0
            ),
            options = listOf("cooking", "smiling", "freezing")
        )
    )

    operator fun invoke(dictionaryWords: List<DictionaryWord>): List<Question> {
        return if (dictionaryWords.size < MIN_WORDS_FOR_TEST) {
            mockQuestions
        } else {
            val questionsList = mutableListOf<Question>()
            dictionaryWords.forEach { word ->
                val incorrectOptions = dictionaryWords
                    .filterNot { it == word }
                    .shuffled()
                    .subList(0, INCORRECT_OPTIONS_COUNT)
                    .map { it.word }

                val question = Question(
                    correctWord = word,
                    options = incorrectOptions + word.word
                )
                // correctWord = word.word,
                // correctWordDefinition = word.meanings.random().definition.definition,

                questionsList.add(question)
            }
            questionsList
        }
    }

    private companion object {
        const val MIN_WORDS_FOR_TEST = 3
        const val INCORRECT_OPTIONS_COUNT = 2
    }

}