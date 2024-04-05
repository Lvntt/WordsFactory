package dev.lantt.wordsfactory.di

import dev.lantt.wordsfactory.auth.domain.usecase.LoginUserUseCase
import dev.lantt.wordsfactory.auth.domain.usecase.RegisterUserUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.GetAllSavedDictionaryWordsUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.GetDictionaryWordUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.PlayAudioUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.SaveDictionaryWordUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.StopAudioUseCase
import dev.lantt.wordsfactory.onboarding.domain.usecase.SetIsOnboardingPassedUseCase
import dev.lantt.wordsfactory.splash.domain.CheckOnboardingPassedUseCase
import dev.lantt.wordsfactory.splash.domain.CheckUserLoginUseCase
import dev.lantt.wordsfactory.video.domain.usecase.FetchLastSavedUrlUseCase
import dev.lantt.wordsfactory.video.domain.usecase.SaveUrlToLocalStorageUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideDomainModule(): Module = module {

    factory { RegisterUserUseCase(get()) }

    factory { LoginUserUseCase(get()) }

    factory { GetDictionaryWordUseCase(get()) }

    factory { SaveDictionaryWordUseCase(get()) }

    factory { PlayAudioUseCase(get()) }

    factory { StopAudioUseCase(get()) }

    factory { FetchLastSavedUrlUseCase(get()) }

    factory { SaveUrlToLocalStorageUseCase(get()) }

    factory { CheckOnboardingPassedUseCase(get()) }

    factory { CheckUserLoginUseCase(get()) }

    factory { SetIsOnboardingPassedUseCase(get()) }

    factory { GetAllSavedDictionaryWordsUseCase(get()) }

}