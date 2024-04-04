package dev.lantt.wordsfactory.dictionary.data.model.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meanings")
data class MeaningEntity(
    @PrimaryKey(autoGenerate = true) val meaningId: Int = 0,
    val word: String,
    val partOfSpeech: String,
    @Embedded val definition: DefinitionEntity
)
