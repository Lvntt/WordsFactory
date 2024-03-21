package dev.lantt.wordsfactory.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dev.lantt.wordsfactory.core.presentation.navigation.RootNavigation
import dev.lantt.wordsfactory.core.presentation.ui.theme.WordsFactoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            WordsFactoryTheme {
                RootNavigation(navController = navController)
            }
        }
    }
}