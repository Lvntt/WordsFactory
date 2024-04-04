package dev.lantt.wordsfactory.dictionary.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "definitions")
data class DefinitionEntity(
    @PrimaryKey(autoGenerate = true) val definitionId: Int = 0,
    val definition: String,
    val example: String?
)
