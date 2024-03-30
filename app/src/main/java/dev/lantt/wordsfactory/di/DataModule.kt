package dev.lantt.wordsfactory.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.lantt.wordsfactory.core.data.datasource.SettingsDataSource
import dev.lantt.wordsfactory.core.data.repository.FirebaseAuthRepository
import dev.lantt.wordsfactory.core.data.repository.SettingsRepositoryImpl
import dev.lantt.wordsfactory.core.domain.repository.AuthRepository
import dev.lantt.wordsfactory.core.domain.repository.SettingsRepository
import dev.lantt.wordsfactory.dictionary.data.audio.AudioRepositoryImpl
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

private fun provideDictionaryWordMapper(context: Context): DictionaryWordMapper =
    DictionaryWordMapper(context)

private fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository =
    FirebaseAuthRepository(firebaseAuth)

private fun provideDictionaryRepository(
    dictionaryApiService: DictionaryApiService,
    dictionaryWordMapper: DictionaryWordMapper
): DictionaryRepository =
    DictionaryRepositoryImpl(dictionaryApiService, dictionaryWordMapper)

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

fun provideDataModule(): Module = module {

    single { provideFirebaseAuth() }

    single { provideDictionaryWordMapper(androidContext().applicationContext) }

    single { provideAuthRepository(get()) }

    single { provideDictionaryRepository(get(), get()) }

    single { provideAudioRepository() }

    single { provideVideoDataSource(androidContext().applicationContext) }

    single { provideVideoRepository(get()) }

    single { provideSettingsDataSource(androidContext().applicationContext) }

    single { provideSettingsRepository(get()) }

}