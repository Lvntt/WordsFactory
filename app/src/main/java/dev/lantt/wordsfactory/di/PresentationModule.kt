package dev.lantt.wordsfactory.di

import dev.lantt.wordsfactory.onboarding.presentation.viewmodel.OnboardingViewModel
import dev.lantt.wordsfactory.splash.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun providePresentationModule(): Module = module {

    viewModel { OnboardingViewModel() }

    viewModel { SplashViewModel() }

}