package dev.lantt.wordsfactory.training.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.dictionary.domain.usecase.GetAllSavedDictionaryWordsUseCase
import dev.lantt.wordsfactory.training.domain.entity.Question
import dev.lantt.wordsfactory.training.domain.usecase.GetTestQuestionsUseCase
import dev.lantt.wordsfactory.training.domain.usecase.HandleOptionChoiceUseCase
import dev.lantt.wordsfactory.training.presentation.state.TestState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TestViewModel(
    private val getAllSavedDictionaryWordsUseCase: GetAllSavedDictionaryWordsUseCase,
    private val getTestQuestionsUseCase: GetTestQuestionsUseCase,
    private val handleOptionChoiceUseCase: HandleOptionChoiceUseCase,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _testState = MutableStateFlow<TestState>(TestState.Loading)
    val testState = _testState.asStateFlow()

    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    private var questionJob: Job? = null

    private val savedDictionaryWords = getAllSavedDictionaryWordsUseCase()
        .stateIn(
            viewModelScope,
            // TODO SharingStarted mode?
            SharingStarted.Lazily,
            emptyList()
        )

    private val testExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e("TestViewModel", exception.message.toString())
    }

    init {
        val questions = getTestQuestionsUseCase(savedDictionaryWords.value)
        _questions.update { questions }

        updateCurrentQuestionJob()
    }

    private fun updateCurrentQuestionJob() {
        questionJob = viewModelScope.launch {
            while (_questions.value.isNotEmpty()) {
                val currentQuestion = _questions.value.last()
                _questions.update { _questions.value.dropLast(1) }
                _testState.update { TestState.Ongoing(currentQuestion) }

                delay(NEW_QUESTION_EMISSION_DELAY_MS)
                // TODO handleOptionChoice
            }
        }
    }

    fun onChooseOption(
        question: Question,
        chosenOption: String
    ) {
        viewModelScope.launch(defaultDispatcher + testExceptionHandler) {
            handleOptionChoiceUseCase(question, chosenOption)
            questionJob?.cancel()
            updateCurrentQuestionJob()
        }
    }

    fun onFinishTraining() {
        _testState.update { TestState.Finished }
    }

    fun onTrainAgain() {
        _testState.update { TestState.Loading }
    }

    private companion object {
        const val NEW_QUESTION_EMISSION_DELAY_MS = 5000L
    }

}