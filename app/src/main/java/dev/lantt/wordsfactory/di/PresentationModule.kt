package dev.lantt.wordsfactory.di

import dev.lantt.wordsfactory.auth.presentation.viewmodel.RegistrationViewModel
import dev.lantt.wordsfactory.dictionary.presentation.viewmodel.DictionaryViewModel
import dev.lantt.wordsfactory.onboarding.presentation.viewmodel.OnboardingViewModel
import dev.lantt.wordsfactory.splash.presentation.SplashViewModel
import dev.lantt.wordsfactory.video.presentation.VideoViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun providePresentationModule(): Module = module {

    viewModel { OnboardingViewModel() }

    viewModel { SplashViewModel() }

    viewModel { RegistrationViewModel(get(), Dispatchers.IO) }

    viewModel { DictionaryViewModel(get(), get(), get(), Dispatchers.IO) }

    viewModel { VideoViewModel(get(), get()) }

}