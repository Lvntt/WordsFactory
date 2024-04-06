package dev.lantt.wordsfactory.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.lantt.wordsfactory.core.data.datasource.SettingsDataSource
import dev.lantt.wordsfactory.core.data.repository.FirebaseAuthRepository
import dev.lantt.wordsfactory.core.data.repository.SettingsRepositoryImpl
import dev.lantt.wordsfactory.core.domain.repository.AuthRepository
import dev.lantt.wordsfactory.core.domain.repository.SettingsRepository
import dev.lantt.wordsfactory.dictionary.data.audio.AudioRepositoryImpl
import dev.lantt.wordsfactory.dictionary.data.dao.DictionaryDao
import dev.lantt.wordsfactory.dictionary.data.db.DictionaryDatabase
import dev.lantt.wordsfactory.dictionary.data.mapper.DictionaryWordMapper
import dev.lantt.wordsfactory.dictionary.data.network.DictionaryApiService
import dev.lantt.wordsfactory.dictionary.data.repository.DictionaryRepositoryImpl
import dev.lantt.wordsfactory.dictionary.domain.repository.AudioRepository
import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository
import dev.lantt.wordsfactory.video.data.datasource.VideoDataSource
import dev.lantt.wordsfactory.video.data.repository.VideoRepositoryImpl
import dev.lantt.wordsfactory.video.domain.repository.VideoRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

private fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

private fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository =
    FirebaseAuthRepository(firebaseAuth)

private fun provideDictionaryRepository(
    dictionaryApiService: DictionaryApiService,
    dictionaryWordMapper: DictionaryWordMapper,
    dictionaryDao: DictionaryDao
): DictionaryRepository =
    DictionaryRepositoryImpl(
        dictionaryApiService,
        dictionaryWordMapper,
        dictionaryDao
    )

private fun provideAudioRepository(): AudioRepository =
    AudioRepositoryImpl()

private fun provideVideoDataSource(context: Context) =
    VideoDataSource(context)

private fun provideVideoRepository(videoDataSource: VideoDataSource): VideoRepository =
    VideoRepositoryImpl(videoDataSource)

private fun provideSettingsDataSource(context: Context): SettingsDataSource =
    SettingsDataSource(context)

private fun provideSettingsRepository(
    settingsDataSource: SettingsDataSource
): SettingsRepository =
    SettingsRepositoryImpl(settingsDataSource)

private fun provideDictionaryDatabase(context: Context): DictionaryDatabase =
    Room.databaseBuilder(
        context,
        DictionaryDatabase::class.java,
        "dictionary_db"
    ).build()

private fun provideDictionaryDao(dictionaryDatabase: DictionaryDatabase) =
    dictionaryDatabase.dictionaryDao()

fun provideDataModule(): Module = module {

    single { provideFirebaseAuth() }

    single { DictionaryWordMapper() }

    single { provideAuthRepository(get()) }

    single { provideDictionaryRepository(get(), get(), get()) }

    single { provideAudioRepository() }

    single { provideVideoDataSource(androidContext().applicationContext) }

    single { provideVideoRepository(get()) }

    single { provideSettingsDataSource(androidContext().applicationContext) }

    single { provideSettingsRepository(get()) }

    single { provideDictionaryDatabase(androidContext().applicationContext) }

    single { provideDictionaryDao(get()) }

}