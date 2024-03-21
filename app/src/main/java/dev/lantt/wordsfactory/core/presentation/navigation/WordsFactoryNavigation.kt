package dev.lantt.wordsfactory.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.lantt.wordsfactory.auth.presentation.AuthScreen
import dev.lantt.wordsfactory.onboarding.presentation.screen.OnboardingScreen
import dev.lantt.wordsfactory.splash.presentation.SplashScreen

object WordsFactoryDestinations {
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val AUTH = "auth"
}

@Composable
fun RootNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = WordsFactoryDestinations.SPLASH
    ) {
        composable(WordsFactoryDestinations.SPLASH) {
            SplashScreen(
                onNavigateToOnboarding = {
                    navController.navigate(WordsFactoryDestinations.ONBOARDING)
                },
                onNavigateToAuth = {
                    navController.navigate(WordsFactoryDestinations.AUTH)
                },
                onNavigateToDictionary = {}
            )
        }
        
        composable(WordsFactoryDestinations.ONBOARDING) {
            OnboardingScreen(
                onNavigateToAuth = {
                    navController.navigate(WordsFactoryDestinations.AUTH)
                }
            )
        }

        composable(WordsFactoryDestinations.AUTH) {
            AuthScreen()
        }
    }
}