package dev.lantt.wordsfactory.dictionary.presentation.state

data class DictionaryState(
    val query: String = "",
    val isAudioPlaying: Boolean = false,
    val canClearQuery: Boolean = true
)