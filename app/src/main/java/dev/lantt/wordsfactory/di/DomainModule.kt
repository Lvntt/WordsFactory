package dev.lantt.wordsfactory.di

import dev.lantt.wordsfactory.auth.domain.usecase.RegisterUserUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideDomainModule(): Module = module {

    factory { RegisterUserUseCase(get()) }

}