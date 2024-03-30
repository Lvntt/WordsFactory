package dev.lantt.wordsfactory.video.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.core.Constants.LEARN_ENGLISH_URL
import dev.lantt.wordsfactory.video.domain.usecase.FetchLastSavedUrlUseCase
import dev.lantt.wordsfactory.video.domain.usecase.SaveUrlToLocalStorageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VideoViewModel(
    private val fetchLastSavedUrlUseCase: FetchLastSavedUrlUseCase,
    private val saveUrlToLocalStorageUseCase: SaveUrlToLocalStorageUseCase
) : ViewModel() {

    private val _lastSavedUrl = MutableStateFlow(LEARN_ENGLISH_URL)
    val lastSavedUrl = _lastSavedUrl.asStateFlow()

    fun saveUrl(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUrlToLocalStorageUseCase(url)
        }
    }

    fun fetchLastSavedUrl() {
        val lastSavedUrl = fetchLastSavedUrlUseCase()
        _lastSavedUrl.update { lastSavedUrl ?: LEARN_ENGLISH_URL }
    }

}