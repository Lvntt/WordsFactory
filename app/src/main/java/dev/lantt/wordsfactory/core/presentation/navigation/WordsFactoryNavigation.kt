package dev.lantt.wordsfactory.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.lantt.wordsfactory.auth.presentation.screen.LoginScreen
import dev.lantt.wordsfactory.auth.presentation.screen.RegistrationScreen
import dev.lantt.wordsfactory.onboarding.presentation.screen.OnboardingScreen
import dev.lantt.wordsfactory.splash.presentation.SplashScreen

object WordsFactoryDestinations {
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val REGISTRATION = "registration"
    const val LOGIN = "login"
    const val DICTIONARY = "dictionary"
    const val TRAINING = "training"
    const val VIDEO = "video"
}

@Composable
fun RootNavigation(
    navController: NavHostController,
    onCloseApp: () -> Unit,
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
                onNavigateToRegistration = {
                    navController.navigate(WordsFactoryDestinations.REGISTRATION)
                },
                onNavigateToDictionary = {
                    navController.navigate(WordsFactoryDestinations.DICTIONARY)
                }
            )
        }
        
        composable(WordsFactoryDestinations.ONBOARDING) {
            OnboardingScreen(
                onNavigateToRegistration = {
                    navController.navigate(WordsFactoryDestinations.REGISTRATION)
                }
            )
        }

        composable(WordsFactoryDestinations.REGISTRATION) {
            RegistrationScreen(
                onNavigateToLogin = {
                    navController.navigate(WordsFactoryDestinations.LOGIN)
                },
                onNavigateToDictionary = {
                    navController.navigate(WordsFactoryDestinations.DICTIONARY)
                }
            )
        }

        composable(WordsFactoryDestinations.LOGIN) {
            LoginScreen(
                onNavigateToRegistration = {
                    navController.navigate(WordsFactoryDestinations.REGISTRATION)
                }
            )
        }

        composable(WordsFactoryDestinations.DICTIONARY) {
            BottomNavigationWrapper(
                onCloseApp = onCloseApp
            )
        }
    }
}