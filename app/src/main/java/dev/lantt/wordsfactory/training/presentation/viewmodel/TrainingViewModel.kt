package dev.lantt.wordsfactory.training.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.training.presentation.event.TrainingEvent
import dev.lantt.wordsfactory.training.presentation.state.TrainingState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TrainingViewModel : ViewModel() {

    private val _trainingState = MutableStateFlow<TrainingState>(TrainingState.Idle)
    val trainingState = _trainingState.asStateFlow()

    private val _trainingEventFlow = MutableSharedFlow<TrainingEvent>()
    val trainingEvents = _trainingEventFlow.asSharedFlow()

    fun onStartTimer() {
        _trainingState.update { TrainingState.TimerCountdown }
    }

    fun onStartTraining() {
        viewModelScope.launch {
            _trainingEventFlow.emit(TrainingEvent.TrainingStarted)
        }
        _trainingState.update { TrainingState.Idle }
    }

}