package dev.lantt.wordsfactory.dictionary.data.audio

import android.media.AudioAttributes
import android.media.MediaPlayer
import dev.lantt.wordsfactory.dictionary.domain.repository.AudioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AudioRepositoryImpl : AudioRepository {

    private var mediaPlayer: MediaPlayer? = null

    override suspend fun play(audioUrl: String) {
        stop()
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            withContext(Dispatchers.IO) {
                setDataSource(audioUrl)
                prepare()
                start()
            }
        }
    }

    override fun stop() {
        mediaPlayer?.apply {
            if (isPlaying) {
                stop()
            }
            release()
        }
        mediaPlayer = null
    }

}