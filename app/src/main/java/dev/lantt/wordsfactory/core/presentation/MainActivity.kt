package dev.lantt.wordsfactory.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.lantt.wordsfactory.core.presentation.ui.theme.WordsFactoryTheme
import dev.lantt.wordsfactory.onboarding.presentation.screen.OnboardingScreen
import dev.lantt.wordsfactory.onboarding.presentation.viewmodel.OnboardingViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = OnboardingViewModel()

        setContent {
            WordsFactoryTheme {
                OnboardingScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}