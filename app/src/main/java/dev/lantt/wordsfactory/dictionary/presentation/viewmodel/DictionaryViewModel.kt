package dev.lantt.wordsfactory.dictionary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.dictionary.domain.usecase.GetDictionaryWordsUseCase
import dev.lantt.wordsfactory.dictionary.presentation.state.DictionaryUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DictionaryViewModel(
    private val getDictionaryWordsUseCase: GetDictionaryWordsUseCase,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _dictionaryUiState = MutableStateFlow<DictionaryUiState>(DictionaryUiState.Initial)
    val dictionaryUiState = _dictionaryUiState.asStateFlow()

    private val dictionaryExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _dictionaryUiState.update { DictionaryUiState.Error(exception.message) }
    }

    fun onQueryChange(query: String) {
        _query.update { query }
    }

    fun onSearch() {
        _dictionaryUiState.update { DictionaryUiState.Loading }
        viewModelScope.launch(defaultDispatcher + dictionaryExceptionHandler) {
            _dictionaryUiState.update {
                DictionaryUiState.Success(
                    getDictionaryWordsUseCase(_query.value)[0]
                )
            }
        }
    }

}