package dev.lantt.wordsfactory.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.splash.domain.CheckOnboardingPassedUseCase
import dev.lantt.wordsfactory.splash.domain.CheckUserLoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val checkUserLoginUseCase: CheckUserLoginUseCase,
    private val checkOnboardingPassedUseCase: CheckOnboardingPassedUseCase
) : ViewModel() {

    private val splashEventChannel = Channel<SplashEvent>()
    val splashEventFlow = splashEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (checkUserLoginUseCase()) {
                splashEventChannel.send(SplashEvent.AuthenticationPassed)
            } else {
                if (checkOnboardingPassedUseCase()) {
                    splashEventChannel.send(SplashEvent.AuthenticationRequired)
                } else {
                    splashEventChannel.send(SplashEvent.OnboardingRequired)
                }
            }
        }
    }

}