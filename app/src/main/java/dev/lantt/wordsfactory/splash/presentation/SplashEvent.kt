package dev.lantt.wordsfactory.splash.presentation

sealed interface SplashEvent {

    data object OnboardingRequired : SplashEvent

    data object AuthenticationRequired : SplashEvent

    data object AuthenticationPassed : SplashEvent

}