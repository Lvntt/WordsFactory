package dev.lantt.wordsfactory.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.lantt.wordsfactory.auth.data.FirebaseAuthRepository
import dev.lantt.wordsfactory.auth.domain.repository.AuthRepository
import dev.lantt.wordsfactory.dictionary.data.network.DictionaryApiService
import dev.lantt.wordsfactory.dictionary.data.repository.DictionaryRepositoryImpl
import dev.lantt.wordsfactory.dictionary.domain.repository.DictionaryRepository
import org.koin.core.module.Module
import org.koin.dsl.module

private fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

private fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository =
    FirebaseAuthRepository(firebaseAuth)

private fun provideDictionaryRepository(
    dictionaryApiService: DictionaryApiService
): DictionaryRepository =
    DictionaryRepositoryImpl(dictionaryApiService)

fun provideDataModule(): Module = module {

    single { provideFirebaseAuth() }

    single { provideAuthRepository(get()) }

    single { provideDictionaryRepository(get()) }

}