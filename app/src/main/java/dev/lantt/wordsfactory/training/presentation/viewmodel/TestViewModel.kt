package dev.lantt.wordsfactory.training.presentation.viewmodel

import android.util.Log
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.core.domain.repository.SettingsManager
import dev.lantt.wordsfactory.dictionary.domain.usecase.GetLeastLearntWordsUseCase
import dev.lantt.wordsfactory.training.domain.entity.Question
import dev.lantt.wordsfactory.training.domain.usecase.GetTestQuestionsUseCase
import dev.lantt.wordsfactory.training.domain.usecase.HandleOptionChoiceUseCase
import dev.lantt.wordsfactory.training.presentation.state.QuestionState
import dev.lantt.wordsfactory.training.presentation.state.TestState
import dev.lantt.wordsfactory.training.presentation.state.TestStatistics
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import java.util.Calendar

class TestViewModel(
    private val getLeastLearntWordsUseCase: GetLeastLearntWordsUseCase,
    private val getTestQuestionsUseCase: GetTestQuestionsUseCase,
    private val handleOptionChoiceUseCase: HandleOptionChoiceUseCase,
    private val settingsManager: SettingsManager,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _testState = MutableStateFlow<TestState>(TestState.Loading)
    val testState = _testState.asStateFlow()

    private val _testStatistics = MutableStateFlow(TestStatistics())

    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    private var questionJob: Job? = null

    private val testExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, exception.message.toString())
    }

    init {
        startTest()
    }

    private fun startTest() {
        viewModelScope.launch {
            getLeastLearntWordsUseCase(MAX_QUESTIONS_COUNT).collect {
                val questions = getTestQuestionsUseCase(it)
                _questions.update { questions }

                updateCurrentQuestionJob()
                this.coroutineContext.job.cancel()
            }
        }
    }

    private fun updateCurrentQuestionJob() {
        questionJob = viewModelScope.launch {
            while (_questions.value.isNotEmpty()) {
                Log.d(TAG, _questions.value.size.toString())
                val currentQuestion = _questions.value.last()
                _testState.update {
                    val testState = it as? TestState.Ongoing
                    TestState.Ongoing(
                        QuestionState(
                            currentQuestion = currentQuestion,
                            correctWordDefinition = currentQuestion.correctWordDefinition.capitalize(Locale.current),
                            selectedWord = null,
                            number = testState?.question?.number?.plus(1) ?: 1,
                            total = testState?.question?.total ?: _questions.value.size
                        )
                    )
                }
                val updatedQuestions = _questions.value.dropLast(1)
                _questions.update { updatedQuestions }

                delay(NEW_QUESTION_EMISSION_DELAY_MS)
                handleOptionChoiceUseCase(currentQuestion, NO_CHOICE_STRING)
                _testStatistics.update {
                    it.copy(incorrectWords = it.incorrectWords + 1)
                }
            }
            onFinishTraining()
        }
    }

    fun onChooseOption(
        question: Question,
        chosenOption: String
    ) {
        viewModelScope.launch(defaultDispatcher + testExceptionHandler) {
            handleOptionChoiceUseCase(question, chosenOption)

            _testStatistics.update {
                if (question.correctWord.word.capitalize(Locale.current) == chosenOption) {
                    it.copy(correctWords = it.correctWords + 1)
                } else {
                    it.copy(incorrectWords = it.incorrectWords + 1)
                }
            }

            val testState = _testState.value as? TestState.Ongoing
            testState?.let {
                _testState.update {
                    testState.copy(
                        question = testState.question.copy(selectedWord = chosenOption)
                    )
                }
            }
            questionJob?.cancel()
            updateCurrentQuestionJob()
        }
    }

    private suspend fun onFinishTraining() {
        val calendar = Calendar.getInstance()
        val currentTimeMillis = calendar.timeInMillis

        settingsManager.setLastTrainingTime(currentTimeMillis)

        _testState.update {
            TestState.Finished(_testStatistics.value)
        }
    }

    fun onTrainAgain() {
        _testState.update { TestState.Loading }
        _testStatistics.update { TestStatistics() }
        startTest()
    }

    private companion object {
        const val TAG = "TestViewModel"
        const val MAX_QUESTIONS_COUNT = 10
        const val NEW_QUESTION_EMISSION_DELAY_MS = 5000L
        const val NO_CHOICE_STRING = "no_choice"
    }

}