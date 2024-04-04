package dev.lantt.wordsfactory.dictionary.data.model.local

import androidx.room.Embedded
import androidx.room.Relation

data class DictionaryWordWithMeanings(
    @Embedded val dictionaryWord: DictionaryWordEntity,
    @Relation(
        parentColumn = "word",
        entityColumn = "word"
    )
    val meanings: List<MeaningEntity>
)
