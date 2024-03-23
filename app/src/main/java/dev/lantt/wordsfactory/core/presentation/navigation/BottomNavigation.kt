package dev.lantt.wordsfactory.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.lantt.wordsfactory.dictionary.presentation.DictionaryScreen

@Composable
fun BottomNavigation(
    bottomNavController: NavHostController
) {
    NavHost(
        navController = bottomNavController,
        startDestination = WordsFactoryDestinations.DICTIONARY
    ) {
        composable(WordsFactoryDestinations.DICTIONARY) {
            DictionaryScreen()
        }

        composable(WordsFactoryDestinations.TRAINING) {

        }

        composable(WordsFactoryDestinations.VIDEO) {

        }
    }
}