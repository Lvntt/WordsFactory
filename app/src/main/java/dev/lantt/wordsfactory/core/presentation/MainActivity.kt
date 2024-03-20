package dev.lantt.wordsfactory.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.lantt.wordsfactory.core.presentation.ui.theme.WordsFactoryTheme
import dev.lantt.wordsfactory.onboarding.presentation.OnboardingScreen
import dev.lantt.wordsfactory.onboarding.presentation.OnboardingViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
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