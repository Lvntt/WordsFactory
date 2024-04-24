package dev.lantt.wordsfactory.training.domain.usecase

import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord
import dev.lantt.wordsfactory.training.domain.entity.Question

class GetTestQuestionsUseCase {

    operator fun invoke(dictionaryWords: List<DictionaryWord>): List<Question> {
        val questionsList = mutableListOf<Question>()
        dictionaryWords.forEach { word ->
            val incorrectOptions = dictionaryWords
                .filterNot { it == word }
                .shuffled()
                .subList(0, INCORRECT_OPTIONS_COUNT)
                .map { it.word }

            val options = (incorrectOptions + word.word).shuffled()

            val question = Question(
                correctWord = word,
                correctWordDefinition = word.meanings.random().definition.definition,
                options = options
            )

            questionsList.add(question)
        }
        return questionsList.shuffled()
    }

    private companion object {
        const val INCORRECT_OPTIONS_COUNT = 2
    }

}