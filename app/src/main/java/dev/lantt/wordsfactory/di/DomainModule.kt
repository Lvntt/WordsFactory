package dev.lantt.wordsfactory.di

import dev.lantt.wordsfactory.auth.domain.usecase.RegisterUserUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.GetDictionaryWordUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.PlayAudioUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.StopAudioUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideDomainModule(): Module = module {

    factory { RegisterUserUseCase(get()) }

    factory { GetDictionaryWordUseCase(get()) }

    factory { PlayAudioUseCase(get()) }

    factory { StopAudioUseCase(get()) }

}