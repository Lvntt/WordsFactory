package dev.lantt.wordsfactory.dictionary.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary_words")
data class DictionaryWordEntity(
    @PrimaryKey val word: String,
    val phonetic: String?,
    val phoneticAudio: String?,
    val learningCoefficient: Int
)
