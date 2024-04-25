package dev.lantt.wordsfactory.dictionary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.dictionary.domain.usecase.DeleteDictionaryWordUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.GetDictionaryWordUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.PlayAudioUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.SaveDictionaryWordUseCase
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
    private val saveDictionaryWordUseCase: SaveDictionaryWordUseCase,
    private val deleteDictionaryWordUseCase: DeleteDictionaryWordUseCase,
    private val playAudioUseCase: PlayAudioUseCase,
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
            it.copy(
                query = query,
                canClearQuery = false
            )
        }
    }

    fun onClearQuery() {
        _dictionaryState.update {
            it.copy(
                query = "",
                canClearQuery = false
            )
        }
    }

    fun onSearch() {
        _dictionaryUiState.update { DictionaryUiState.Loading }
        viewModelScope.launch(defaultDispatcher + dictionaryExceptionHandler) {
            val dictionaryWord = getDictionaryWordUseCase(_dictionaryState.value.query)
            if (dictionaryWord == null) {
                _dictionaryUiState.update {
                    DictionaryUiState.NotFound
                }
            } else {
                _dictionaryUiState.update {
                    DictionaryUiState.Success(dictionaryWord)
                }
            }

            _dictionaryState.update {
                it.copy(canClearQuery = true)
            }
        }
    }

    fun onSaveDictionaryWord() {
        viewModelScope.launch(defaultDispatcher + dictionaryExceptionHandler) {
            val currentWord = (_dictionaryUiState.value as DictionaryUiState.Success).word
            saveDictionaryWordUseCase(currentWord)
            _dictionaryUiState.update {
                DictionaryUiState.Success(currentWord.copy(isCached = true))
            }
        }
    }

    fun onDeleteDictionaryWord() {
        viewModelScope.launch(defaultDispatcher + dictionaryExceptionHandler) {
            val currentWord = (_dictionaryUiState.value as DictionaryUiState.Success).word
            deleteDictionaryWordUseCase(currentWord.word)
            _dictionaryUiState.update {
                DictionaryUiState.Success(currentWord.copy(isCached = false))
            }
        }
    }

    fun onPlayAudio(audioUrl: String) {
        viewModelScope.launch(defaultDispatcher + dictionaryExceptionHandler) {
            playAudioUseCase(audioUrl)
        }
    }

}