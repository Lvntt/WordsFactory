package dev.lantt.wordsfactory.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.lantt.wordsfactory.dictionary.presentation.screen.DictionaryScreen
import dev.lantt.wordsfactory.training.presentation.screen.TrainingScreen
import dev.lantt.wordsfactory.video.presentation.VideoScreen

@Composable
fun BottomNavigation(
    bottomNavController: NavHostController,
    rootNavController: NavHostController
) {
    NavHost(
        navController = bottomNavController,
        startDestination = WordsFactoryDestinations.DICTIONARY
    ) {
        composable(WordsFactoryDestinations.DICTIONARY) {
            DictionaryScreen()
        }

        composable(WordsFactoryDestinations.TRAINING) {
            TrainingScreen(
                onNavigateToTestQuestion = {
                    rootNavController.navigate(WordsFactoryDestinations.TEST_QUESTION)
                }
            )
        }

        composable(WordsFactoryDestinations.VIDEO) {
            VideoScreen()
        }
    }
}