package dev.lantt.wordsfactory.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.lantt.wordsfactory.auth.data.FirebaseAuthRepository
import dev.lantt.wordsfactory.auth.domain.repository.AuthRepository
import dev.lantt.wordsfactory.dictionary.data.audio.AudioRepositoryImpl
import dev.lantt.wordsfactory.dictionary.data.mapper.DictionaryWordMapper
import dev.lantt.wordsfactory.dictionary.data.network.DictionaryApiService
import dev.lantt.wordsfactory.dictionary.data.repository.DictionaryRepositoryImpl
import dev.lantt.wordsfactory.dictionary.domain.repository.AudioRepository
import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository
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

fun provideDataModule(): Module = module {

    single { provideFirebaseAuth() }

    single { provideDictionaryWordMapper(androidContext().applicationContext) }

    single { provideAuthRepository(get()) }

    single { provideDictionaryRepository(get(), get()) }

    single { provideAudioRepository() }

}