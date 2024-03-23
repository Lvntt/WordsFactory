package dev.lantt.wordsfactory.dictionary.presentation.state

import dev.lantt.wordsfactory.dictionary.domain.entity.DictionaryWord

sealed interface DictionaryUiState {

    data object Initial : DictionaryUiState

    data object Loading : DictionaryUiState

    data class Error(val message: String?) : DictionaryUiState

    data class Success(val word: DictionaryWord) : DictionaryUiState

}