package dev.lantt.wordsfactory.training.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dev.lantt.wordsfactory.training.presentation.state.TestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TestViewModel : ViewModel() {

    private val _testState = MutableStateFlow<TestState>(TestState.Ongoing)
    val testState = _testState.asStateFlow()

    fun onFinishTraining() {
        _testState.update { TestState.Finished }
    }

    fun onTrainAgain() {
        _testState.update { TestState.Ongoing }
    }

}