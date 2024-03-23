package dev.lantt.wordsfactory.di

import dev.lantt.wordsfactory.auth.domain.usecase.RegisterUserUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.GetDictionaryWordsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideDomainModule(): Module = module {

    factory { RegisterUserUseCase(get()) }

    factory { GetDictionaryWordsUseCase(get()) }

}