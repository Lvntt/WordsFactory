package dev.lantt.wordsfactory.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lantt.wordsfactory.core.domain.repository.SettingsManager
import dev.lantt.wordsfactory.splash.domain.CheckUserLoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val checkUserLoginUseCase: CheckUserLoginUseCase,
    private val settingsManager: SettingsManager
) : ViewModel() {

    private val splashEventChannel = Channel<SplashEvent>()
    val splashEventFlow = splashEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val isUserLoggedIn = checkUserLoginUseCase()
            val isOnboardingPassed = settingsManager.isOnboardingPassed()

            splashEventChannel.send(
                when {
                    isUserLoggedIn -> SplashEvent.AuthenticationPassed
                    isOnboardingPassed && !isUserLoggedIn -> SplashEvent.AuthenticationRequired
                    else -> SplashEvent.OnboardingRequired
                }
            )
        }
    }

}