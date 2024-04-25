package dev.lantt.wordsfactory.di

import dev.lantt.wordsfactory.auth.presentation.viewmodel.LoginViewModel
import dev.lantt.wordsfactory.auth.presentation.viewmodel.RegistrationViewModel
import dev.lantt.wordsfactory.dictionary.presentation.viewmodel.DictionaryViewModel
import dev.lantt.wordsfactory.onboarding.presentation.viewmodel.OnboardingViewModel
import dev.lantt.wordsfactory.splash.presentation.SplashViewModel
import dev.lantt.wordsfactory.training.presentation.viewmodel.TestViewModel
import dev.lantt.wordsfactory.training.presentation.viewmodel.TrainingViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun providePresentationModule(): Module = module {

    viewModel { OnboardingViewModel(get()) }

    viewModel { SplashViewModel(get(), get()) }

    viewModel { RegistrationViewModel(get(), Dispatchers.IO) }

    viewModel { LoginViewModel(get(), Dispatchers.IO) }

    viewModel { DictionaryViewModel(get(), get(), get(), get(), get(), Dispatchers.IO) }

    viewModel { TrainingViewModel(get(), get()) }

    viewModel { TestViewModel(get(), get(), get(), get(), get(), Dispatchers.IO) }

}