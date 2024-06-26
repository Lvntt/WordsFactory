package dev.lantt.wordsfactory.training.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.core.domain.repository.SettingsManager
import dev.lantt.wordsfactory.dictionary.domain.usecase.GetAllSavedDictionaryWordsUseCase
import dev.lantt.wordsfactory.training.presentation.event.TrainingEvent
import dev.lantt.wordsfactory.training.presentation.state.PermissionDialogState
import dev.lantt.wordsfactory.training.presentation.state.TrainingState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TrainingViewModel(
    getAllSavedDictionaryWordsUseCase: GetAllSavedDictionaryWordsUseCase,
    private val settingsManager: SettingsManager
) : ViewModel() {

    private val _trainingState = MutableStateFlow<TrainingState>(TrainingState.Idle)
    val trainingState = _trainingState.asStateFlow()

    private val _permissionDialogState = MutableStateFlow(PermissionDialogState())
    val permissionDialogState = _permissionDialogState.asStateFlow()

    private val _trainingEventFlow = MutableSharedFlow<TrainingEvent>()
    val trainingEvents = _trainingEventFlow.asSharedFlow()

    val savedDictionaryWords = getAllSavedDictionaryWordsUseCase().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    init {
        _permissionDialogState.update {
            it.copy(shouldShow = !settingsManager.wasNotificationPermissionAsked())
        }
    }

    fun onShowDialog() {
        _permissionDialogState.update {
            it.copy(isShown = true)
        }
    }

    fun onDismissDialog() {
        _permissionDialogState.update {
            it.copy(isShown = false)
        }
        viewModelScope.launch {
            settingsManager.setNotificationPermissionAsked()
        }
    }

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