package dev.lantt.wordsfactory.dictionary.domain.repository

interface AudioRepository {

    suspend fun play(audioUrl: String)

    fun stop()

}