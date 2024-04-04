package dev.lantt.wordsfactory.dictionary.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.lantt.wordsfactory.dictionary.data.dao.DictionaryDao
import dev.lantt.wordsfactory.dictionary.data.model.local.DefinitionEntity
import dev.lantt.wordsfactory.dictionary.data.model.local.DictionaryWordEntity
import dev.lantt.wordsfactory.dictionary.data.model.local.MeaningEntity

@Database(
    entities = [
        DictionaryWordEntity::class,
        MeaningEntity::class,
        DefinitionEntity::class
    ],
    version = 1
)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
}