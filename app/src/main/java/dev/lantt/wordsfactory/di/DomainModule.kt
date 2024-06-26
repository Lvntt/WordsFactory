package dev.lantt.wordsfactory.di

import dev.lantt.wordsfactory.auth.domain.usecase.LoginUserUseCase
import dev.lantt.wordsfactory.auth.domain.usecase.RegisterUserUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.DeleteDictionaryWordUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.GetAllSavedDictionaryWordsUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.GetDictionaryWordUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.PlayAudioUseCase
import dev.lantt.wordsfactory.dictionary.domain.usecase.SaveDictionaryWordUseCase
import dev.lantt.wordsfactory.splash.domain.CheckUserLoginUseCase
import dev.lantt.wordsfactory.training.domain.usecase.GetNewLearningCoefficientUseCase
import dev.lantt.wordsfactory.training.domain.usecase.GetTestQuestionsUseCase
import dev.lantt.wordsfactory.training.domain.usecase.GetWordsForQuestionsUseCase
import dev.lantt.wordsfactory.training.domain.usecase.UpdateDictionaryWordUseCase
import dev.lantt.wordsfactory.video.domain.usecase.FetchLastSavedUrlUseCase
import dev.lantt.wordsfactory.video.domain.usecase.SaveUrlToLocalStorageUseCase
import dev.lantt.wordsfactory.widget.domain.usecase.GetDictionaryWordsCountUseCase
import dev.lantt.wordsfactory.widget.domain.usecase.GetLearntDictionaryWordsCountUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideDomainModule(): Module = module {

    factory { RegisterUserUseCase(get()) }

    factory { LoginUserUseCase(get()) }

    factory { GetDictionaryWordUseCase(get()) }

    factory { SaveDictionaryWordUseCase(get()) }

    factory { PlayAudioUseCase(get()) }

    factory { FetchLastSavedUrlUseCase(get()) }

    factory { SaveUrlToLocalStorageUseCase(get()) }

    factory { CheckUserLoginUseCase(get()) }

    factory { GetAllSavedDictionaryWordsUseCase(get()) }

    factory { DeleteDictionaryWordUseCase(get()) }

    factory { GetTestQuestionsUseCase() }

    factory { GetNewLearningCoefficientUseCase() }

    factory { GetWordsForQuestionsUseCase(get()) }

    factory { UpdateDictionaryWordUseCase(get()) }

    factory { GetDictionaryWordsCountUseCase(get()) }

    factory { GetLearntDictionaryWordsCountUseCase(get()) }

}