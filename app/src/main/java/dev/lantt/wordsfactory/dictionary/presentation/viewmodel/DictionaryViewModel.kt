package dev.lantt.wordsfactory.dictionary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.dictionary.domain.usecase.GetDictionaryWordUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.PlayAudioUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.StopAudioUseCase
import dev.lantt.wordsfactory.dictionary.presentation.state.DictionaryState
import dev.lantt.wordsfactory.dictionary.presentation.state.DictionaryUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DictionaryViewModel(
    private val getDictionaryWordUseCase: GetDictionaryWordUseCase,
    private val playAudioUseCase: PlayAudioUseCase,
    private val stopAudioUseCase: StopAudioUseCase,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _dictionaryState = MutableStateFlow(DictionaryState())
    val dictionaryState = _dictionaryState.asStateFlow()

    private val _dictionaryUiState = MutableStateFlow<DictionaryUiState>(DictionaryUiState.Initial)
    val dictionaryUiState = _dictionaryUiState.asStateFlow()

    private val dictionaryExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _dictionaryUiState.update { DictionaryUiState.Error(exception.message) }
    }

    fun onQueryChange(query: String) {
        _dictionaryState.update {
            it.copy(query = query)
        }
    }

    fun onSearch() {
        _dictionaryUiState.update { DictionaryUiState.Loading }
        viewModelScope.launch(defaultDispatcher + dictionaryExceptionHandler) {
            _dictionaryUiState.update {
                DictionaryUiState.Success(
                    getDictionaryWordUseCase(_dictionaryState.value.query)
                )
            }
        }
    }

    fun onPlayAudio(audioUrl: String) {
        viewModelScope.launch(defaultDispatcher + dictionaryExceptionHandler) {
            playAudioUseCase(audioUrl)
        }
    }

    fun onStopAudio() {
        stopAudioUseCase()
    }

}